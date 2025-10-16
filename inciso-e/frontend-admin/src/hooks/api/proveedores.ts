import { Proveedor, ProveedoresMetrics } from '@/api/entities';
import { createDeleteMutationHook, createGetQueryHook, createPaginationQueryHook, SortableQueryParams } from '@/api/helpers';
import { notifications } from '@mantine/notifications';

const QUERY_KEY = 'proveedores';
const BASE_ENDPOINT = 'proveedores';

export const useGetProveedores = createPaginationQueryHook<
  typeof Proveedor,
  SortableQueryParams
>({
  endpoint: BASE_ENDPOINT,
  dataSchema: Proveedor,
  rQueryParams: { queryKey: [QUERY_KEY] },
});

export const useGetProveedoresMetrics = createGetQueryHook({
  endpoint: `${BASE_ENDPOINT}/metrics`,
  responseSchema: ProveedoresMetrics,
  rQueryParams: { queryKey: [QUERY_KEY, { resource: 'metrics' }] },
});

export const useDeleteProveedor = createDeleteMutationHook<
  typeof Proveedor,
  { id: number }
>({
  endpoint: `${BASE_ENDPOINT}/:id`,
  rMutationParams: {
    onSuccess: (data, variables, context, queryClient) => {
      queryClient.invalidateQueries({ queryKey: [QUERY_KEY] });
      notifications.show({
        title: 'Borrado',
        message: 'El proveedor fue borrado con éxito',
        color: 'green',
      });
    },
    onError: (error) => {
      notifications.show({
        title: 'Error',
        message: error.message || 'No se pudo borrar el proveedor',
        color: 'red',
      });
    },
  },
});

// export const useDeleteProveedor = createDeleteMutationHook<
//   typeof Proveedor,
//   { id: string }
// >({
//   endpoint: '/proveedores/:id',
//   rMutationParams: {
//     onSuccess: (_, variables, __, queryClient) => {
//       queryClient.invalidateQueries('getProveedores'); // refresh list
//       notifications.show({
//         title: 'Deleted',
//         message: 'Proveedor deleted successfully',
//         color: 'green',
//       });
//     },
//     onError: (error) => {
//       notifications.show({
//         title: 'Error',
//         message: error.message || 'Failed to delete proveedor',
//         color: 'red',
//       });
//     },
//   },
// });
