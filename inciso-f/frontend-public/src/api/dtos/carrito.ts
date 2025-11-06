import { z } from 'zod';


export const DetalleDetailDto = z.object({
  id: z.number().int(),
  articuloId: z.number().int(),
  nombre: z.string(),
  precio: z.number().min(0),
  imagenId: z.number().int(),
});
export type DetalleDetailDto = z.infer<typeof DetalleDetailDto>;


export const CarritoDetailDto = z.object({
  total: z.number().min(0),
  detalles: z.array(DetalleDetailDto), // nested array
});
export type CarritoDetailDto = z.infer<typeof CarritoDetailDto>;

