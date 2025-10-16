import { useState } from 'react';
import { QueryClient, useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { isAxiosError } from 'axios';
import { z, ZodError } from 'zod';
import { client } from './axios';

/**
 * Create a URL with query parameters and route parameters
 *
 * @param {string} base - The base URL with route parameters
 * @param {Object} [queryParams] - The query parameters
 * @param {Object} [routeParams] - The route parameters
 * @returns {string} The URL with query parameters
 */
function createUrl(base, queryParams, routeParams) {
  const url = Object.entries(routeParams ?? {}).reduce(
    (acc, [key, value]) => acc.replaceAll(`:${key}`, String(value)),
    base
  );

  if (!queryParams) return url;

  const query = new URLSearchParams();

  Object.entries(queryParams).forEach(([key, value]) => {
    if (value === undefined || value === null || value === '') return;
    query.append(key, String(value));
  });

  return `${url}?${query.toString()}`;
}

function getQueryKey(queryKey, route = {}, query = {}) {
  const [mainKey, otherKeys = {}] = queryKey;
  return [mainKey, { ...otherKeys, ...route, ...query }];
}

function handleRequestError(error) {
  if (isAxiosError(error)) {
    throw error.response?.data;
  }

  if (error instanceof ZodError) {
    console.error(error.format());
  }

  console.log(error);
  throw error;
}

/* ----------------------------------- GET ---------------------------------- */

/**
 * Create a custom hook for performing GET requests with react-query and Zod validation
 */
export function createGetQueryHook({ endpoint, responseSchema, rQueryParams }) {
  const queryFn = async (params) => {
    const url = createUrl(endpoint, params?.query, params?.route);
    return client
      .get(url)
      .then((response) => responseSchema.parse(response.data))
      .catch(handleRequestError);
  };

  return (params) =>
    useQuery({
      ...rQueryParams,
      queryKey: getQueryKey(rQueryParams.queryKey, params?.route, params?.query),
      queryFn: () => queryFn(params),
    });
}

/* ---------------------------------- POST ---------------------------------- */

export function createPostMutationHook({
  endpoint,
  bodySchema,
  responseSchema,
  rMutationParams,
  options,
}) {
  return (params) => {
    const queryClient = useQueryClient();
    const baseUrl = createUrl(endpoint, params?.query, params?.route);

    const mutationFn = async ({ variables, route, query }) => {
      const url = createUrl(baseUrl, query, route);

      const config = options?.isMultipart
        ? { headers: { 'Content-Type': 'multipart/form-data' } }
        : undefined;

      return client
        .post(url, bodySchema.parse(variables), config)
        .then((response) => responseSchema.parse(response.data))
        .catch(handleRequestError);
    };

    return useMutation({
      ...rMutationParams,
      mutationFn,
      onSuccess: (data, variables, context) =>
        rMutationParams?.onSuccess?.(data, variables, context, queryClient),
      onError: (error, variables, context) =>
        rMutationParams?.onError?.(error, variables, context, queryClient),
      onSettled: (data, error, variables, context) =>
        rMutationParams?.onSettled?.(data, error, variables, context, queryClient),
    });
  };
}

/* ----------------------------------- PUT ---------------------------------- */

export function createPutMutationHook({
  endpoint,
  bodySchema,
  responseSchema,
  rMutationParams,
  options,
}) {
  return (params) => {
    const queryClient = useQueryClient();
    const baseUrl = createUrl(endpoint, params?.query, params?.route);

    const mutationFn = async ({ variables, route, query }) => {
      const url = createUrl(baseUrl, query, route);

      const config = options?.isMultipart
        ? { headers: { 'Content-Type': 'multipart/form-data' } }
        : undefined;

      return client
        .put(url, bodySchema.parse(variables), config)
        .then((response) => responseSchema.parse(response.data))
        .catch(handleRequestError);
    };

    return useMutation({
      ...rMutationParams,
      mutationFn,
      onSuccess: (data, variables, context) =>
        rMutationParams?.onSuccess?.(data, variables, context, queryClient),
      onError: (error, variables, context) =>
        rMutationParams?.onError?.(error, variables, context, queryClient),
      onSettled: (data, error, variables, context) =>
        rMutationParams?.onSettled?.(data, error, variables, context, queryClient),
    });
  };
}

/* --------------------------------- DELETE --------------------------------- */

export function createDeleteMutationHook({ endpoint, rMutationParams }) {
  return (params) => {
    const queryClient = useQueryClient();
    const baseUrl = createUrl(endpoint, params?.query, params?.route);

    const mutationFn = async ({ route, query }) => {
      const url = createUrl(baseUrl, query, route);
      await client.delete(url);
    };

    return useMutation({
      ...rMutationParams,
      mutationFn,
      onSuccess: (data, variables, context) =>
        rMutationParams?.onSuccess?.(data, variables, context, queryClient),
      onError: (error, variables, context) =>
        rMutationParams?.onError?.(error, variables, context, queryClient),
      onSettled: (data, error, variables, context) =>
        rMutationParams?.onSettled?.(data, error, variables, context, queryClient),
    });
  };
}

/* ------------------------------- PAGINATION ------------------------------- */

export function usePagination(params) {
  const [page, setPage] = useState(params?.page ?? 0);
  const [size, setSize] = useState(params?.size ?? 25);

  const onChangeSize = (value) => {
    setSize(value);
    setPage(0);
  };

  return { page, size, setPage, setSize: onChangeSize };
}

export const SpringPaginationMetaSchema = z.object({
  totalElements: z.number().int().min(0),
  totalPages: z.number().int().min(1),
  size: z.number().int().positive(),
  number: z.number().int().min(0),
  sort: z.any().optional(),
  pageable: z.any().optional(),
});

export function createPaginationQueryHook({ endpoint, dataSchema, rQueryParams }) {
  const queryFn = async (params) => {
    const url = createUrl(endpoint, params?.query, params?.route);

    const schema = z.object({
      content: dataSchema.array(),
      totalElements: z.number().int().min(0),
      totalPages: z.number().int().min(1),
      size: z.number().int().positive(),
      number: z.number().int().min(0),
      sort: z.any().optional(),
      pageable: z.any().optional(),
    });

    return client
      .get(url)
      .then((response) => schema.parse(response.data))
      .catch(handleRequestError);
  };

  return (params) => {
    const query = { page: 0, size: 25, ...params?.query };
    const route = params?.route ?? {};

    return useQuery({
      ...rQueryParams,
      queryKey: getQueryKey(rQueryParams.queryKey, route, query),
      queryFn: () => queryFn({ query, route }),
      select: (data) => ({
        data: data.content,
        meta: {
          totalElements: data.totalElements,
          totalPages: data.totalPages,
          size: data.size,
          number: data.number,
        },
      }),
    });
  };
}
