import { Autor, AutoresMetrics } from '@/api/entities';
import { createGetQueryHook, createPaginationQueryHook, SortableQueryParams } from '@/api/helpers';

const QUERY_KEY = 'autores';
const BASE_ENDPOINT = 'autores';

export const useGetAutores = createPaginationQueryHook<
  typeof Autor,
  SortableQueryParams & { status?: Autor['status'] }
>({
  endpoint: BASE_ENDPOINT,
  dataSchema: Autor,
  rQueryParams: { queryKey: [QUERY_KEY] },
});

export const useGetAutoresMetrics = createGetQueryHook({
  endpoint: `${BASE_ENDPOINT}/metrics`,
  responseSchema: AutoresMetrics,
  rQueryParams: { queryKey: [QUERY_KEY, { resource: 'metrics' }] },
});
