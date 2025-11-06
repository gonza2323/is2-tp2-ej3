import { z } from 'zod';

export const ProveedorCreateDto = z.object({
  nombre: z.string().nonempty("El nombre no puede estar vacío"),
  latitud: z
    .number({
      required_error: "La latitud es obligatoria",
      invalid_type_error: "La latitud debe ser un número",
    })
    .min(-90, "La latitud mínima es -90")
    .max(90, "La latitud máxima es 90"),
  longitud: z
    .number({
      required_error: "La longitud es obligatoria",
      invalid_type_error: "La longitud debe ser un número",
    })
    .min(-180, "La longitud mínima es -180")
    .max(180, "La longitud máxima es 180"),
});
export type ProveedorCreateDto = z.infer<typeof ProveedorCreateDto>;

export const ProveedorDetailDto = z.object({
  id: z.number(),
  nombre: z.string().min(1),
  latitud: z
    .number()
    .min(-90)
    .max(90),
  longitud: z
    .number()
    .min(-180)
    .max(180),
});
export type ProveedorDetailDto = z.infer<typeof ProveedorDetailDto>;

export const ProveedorSummaryDto = ProveedorDetailDto;
export type ProveedorSummaryDto = z.infer<typeof ProveedorSummaryDto>;

export const ProveedorUpdateDto = ProveedorDetailDto;
export type ProveedorUpdateDto = z.infer<typeof ProveedorUpdateDto>;
