import { Box, Button, Grid, Group, NumberInput, TextInput } from '@mantine/core';
import { paths } from '@/routes';
import { useForm, zodResolver } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { ProveedorCreateDto } from '@/api/dtos';
import { useCreateProveedor } from '@/hooks';
import { NavLink, useNavigate } from 'react-router-dom';

export default function ProveedorCreateForm() {
  const navigate = useNavigate();
  const createProveedor = useCreateProveedor();

  const form = useForm({
    validate: zodResolver(ProveedorCreateDto),
    initialValues: {
      nombre: "",
      latitud: undefined,
      longitud: undefined,
    },
  });

  const handleSubmit = form.onSubmit((values) => {
    createProveedor.mutate(
      { variables: values },
      {
        onSuccess: () => {
          notifications.show({
            title: "Éxito",
            message: "Proveedor creado correctamente",
          });
          navigate(paths.dashboard.management.proveedores.list);
        },
        onError: (error) => {
          notifications.show({
            title: "Error",
            message:
              error instanceof Error
                ? error.message
                : "Ocurrió un error inesperado",
            color: "red",
          });
        },
      }
    );
  });

  return (
    <Box component="form" onSubmit={handleSubmit} maw={400}>
      <TextInput
        label="Nombre"
        placeholder="Ingrese el nombre"
        {...form.getInputProps("nombre")}
      />

      <NumberInput
        label="Latitud"
        placeholder="Ej: -34.6037"
        step={0.0001}
        decimalScale={6}
        min={-90}
        max={90}
        {...form.getInputProps("latitud")}
        mt="md"
      />

      <NumberInput
        label="Longitud"
        placeholder="Ej: -58.3816"
        step={0.0001}
        decimalScale={6}
        min={-180}
        max={180}
        {...form.getInputProps("longitud")}
        mt="md"
      />

      <Group justify="flex-end" mt="md">
        <Button
          variant="outline"
          component={NavLink}
          to={paths.dashboard.management.proveedores.list}
        >
          Cancelar
        </Button>
        <Button type="submit" loading={createProveedor.isPending}>
          Crear
        </Button>
      </Group>
    </Box>
  );
}