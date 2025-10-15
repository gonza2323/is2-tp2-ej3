import { z } from 'zod';

export const User = z.object({
  id: z.number().int(),
  nombre: z.string().min(1),
});

export type User = z.infer<typeof User>;
