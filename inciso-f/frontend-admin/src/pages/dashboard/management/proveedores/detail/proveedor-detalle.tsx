import { Alert, Button, Card, Group, Loader, Stack, Text } from '@mantine/core';
import { paths } from '@/routes';
import { NavLink } from 'react-router-dom';
import { useGetProveedor } from '@/hooks';

export default function ProveedorDetalle({ proveedorId } : { proveedorId: string }) {
  const { data: proveedor, isLoading, error } = useGetProveedor({ route: { id: proveedorId } });

  if (isLoading) return <Loader />;
  if (error) return <Alert color="red" maw={400}>Error al cargar proveedor</Alert>;

  return (
    <Card shadow="sm" padding="lg" maw={500}>
      <Stack gap="sm">
        <Group>
          <Text color="dimmed">Nombre:</Text>
          <Text>{proveedor?.nombre || '-'}</Text>
        </Group>

      </Stack>

      <Group justify='flex-end' mt="md">
        <Button
          variant="outline"
          component={NavLink} to={paths.dashboard.management.proveedores.list}
        >
          Volver
        </Button>
      </Group>
    </Card>
  );
}