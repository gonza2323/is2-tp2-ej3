import { Box, Button, Grid, Group, TextInput } from '@mantine/core';
import { paths } from '@/routes';
import { useForm, zodResolver } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { ProveedorCreateDto } from '@/api/dtos';
import { useCreateProveedor } from '@/hooks';
import { NavLink, useNavigate } from 'react-router-dom';

export default function ProveedorCreateForm() {
  const navigate = useNavigate();
  const form = useForm({
    validate: zodResolver(ProveedorCreateDto),
    initialValues: { nombre: "" },
  });

  const createProveedor = useCreateProveedor();

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

      <Group justify="flex-end" mt="md">
        <Button variant='outline' component={NavLink} to={paths.dashboard.management.proveedores.list}>
          Cancelar
        </Button>
        <Button type="submit" loading={createProveedor.isPending}>
          Crear
        </Button>
      </Group>
    </Box>
  );
}
