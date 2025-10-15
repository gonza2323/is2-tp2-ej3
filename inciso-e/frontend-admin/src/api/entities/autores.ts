import { z } from 'zod';
import { dateSchema } from '@/utilities/date';
import { phoneNumberSchema } from '@/utilities/phone-number';

export const Autor = z.object({
  id: z.string().cuid2(),
  number: z.string().min(1),
  fullName: z.string().min(1),
  displayName: z.string().min(1),
  email: z.string().email(),
  phoneNumber: phoneNumberSchema,
  avatarUrl: z.string().url().nullable(),
  status: z.enum(['active', 'banned', 'archived']),
  rating: z.number().min(1).max(5),
  createdAt: dateSchema,
  updatedAt: dateSchema,
});

export type Autor = z.infer<typeof Autor>;

export const AutoresMetrics = z.object({
  total: z.number(),
  active: z.number(),
  banned: z.number(),
  archived: z.number(),
});

export type AutoresMetrics = z.infer<typeof AutoresMetrics>;
