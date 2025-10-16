import { z } from 'zod';

export const Proveedor = z.object({
  id: z.number(),
  nombre: z.string().min(1),
});

export type Proveedor = z.infer<typeof Proveedor>;

export const ProveedoresMetrics = z.object({
  total: z.number(),
  active: z.number(),
  banned: z.number(),
  archived: z.number(),
});

export type ProveedoresMetrics = z.infer<typeof ProveedoresMetrics>;
