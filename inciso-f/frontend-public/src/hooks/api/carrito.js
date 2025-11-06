import { ArticuloDetailDto, ArticuloSummaryDto, ArticuloCreateDto } from '../../api/dtos';
import { CarritoDetailDto } from '../../api/dtos/carrito';
import {
  createDeleteMutationHook,
  createGetQueryHook,
  createPostMutationHook,
} from '../../api/helpers';
import { notifications } from '@mantine/notifications';
import { z } from 'zod';

const QUERY_KEY = 'carrito';
const BASE_ENDPOINT = 'carrito';

export const useGetCarrito = createGetQueryHook({
  endpoint: BASE_ENDPOINT,
  responseSchema: CarritoDetailDto,
  rQueryParams: { queryKey: [QUERY_KEY] },
});

export const useAddArticulo = createPostMutationHook({
  endpoint: `${BASE_ENDPOINT}/:id`,
  bodySchema: z.undefined(),
  responseSchema: z.any(),
  rMutationParams: {
    onSuccess: () => {
      console.log("Item added to cart");
    },
  },
});

export const useRemoveItem = createDeleteMutationHook({
  endpoint: `${BASE_ENDPOINT}/:id`,
  rMutationParams: {
    onSuccess: (data, variables, context, queryClient) => {
      queryClient.invalidateQueries({ queryKey: [QUERY_KEY] });
      notifications.show({
        title: 'Borrado',
        message: 'El artículo fue removido del carrito',
        color: 'green',
      });
    },
    onError: (error) => {
      notifications.show({
        title: 'Error',
        message: error?.message || 'No se pudo remover el artículo del carrito',
        color: 'red',
      });
    },
  },
});
