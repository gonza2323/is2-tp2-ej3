import { Proveedor, ProveedoresMetrics } from '@/api/entities';
import { createGetQueryHook, createPaginationQueryHook, SortableQueryParams } from '@/api/helpers';

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
