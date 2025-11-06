import { usePagination } from '../../api/helpers';
import { AddButton } from '../../components/add-button';
import { useGetArticulos, useDeleteArticulo } from '../../hooks';
import { paths } from '../../routes/paths';
import { formatCurrency } from '../../utilities/number';
import { NavLink, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import {
  Text,
  Grid,
  Group,
  Select,
  Card,
  Image,
  Container,
  Pagination,
  Stack,
  Button,
  Divider,
} from '@mantine/core';
import { app } from '../../config';
import { useGetCarrito, useRemoveItem } from '../../hooks/api/carrito';
import { modals } from '@mantine/modals';

export function CartPage() {
  const navigate = useNavigate();
  const { data, isLoading } = useGetCarrito({route: { }});
  const removeFromCart = useRemoveItem();

  const detalles = data?.detalles ?? [];
  const total = data?.total ?? 0;

  const handleDelete = (detalleId) => {

    modals.openConfirmModal({
      title: 'Confirmar borrado',
      children: <Text>¿Está seguro de que desea borrar el proveedor?</Text>,
      labels: { confirm: 'Delete', cancel: 'Cancel' },
      confirmProps: { color: 'red' },
      onConfirm: () => {
        removeFromCart.mutate({
          model: { },
          route: { id: detalleId },
        });
      },
    });
  };

  if (isLoading) {
    return (
      <Container size="lg" py="md">
        <Text align="center" mt="xl">Loading...</Text>
      </Container>
    );
  }

  if (detalles.length === 0) {
    return (
      <Container size="lg" py="md">
        <Text align="center" mt="xl">Your cart is empty</Text>
      </Container>
    );
  }

  return (
    <Grid size="lg" py="md" m={48}>
      <Grid.Col spacing="md" w="100%">
        {detalles.map((detalle) => (
          <Card
            key={detalle.id}
            shadow="sm"
            padding="md"
            radius="md"
            withBorder
            style={{ display: "flex", position: "relative", alignItems: "flex-start" }}
          >
            <Group>

            <Button
              variant="outline"
              color="red"
              size="xs"
              style={{ position: "absolute", top: 8, right: 8 }}
              onClick={() => handleDelete(detalle.id)}
            >
              ✕
            </Button>

            <NavLink to={paths.articulos.view(detalle.articuloId)}>
            <Image
              src={`${app.apiBaseUrl}/imagenes/${detalle.imagenId}`}
              alt={detalle.nombre}
              w={100}
              h={100}
              fit="cover"
              radius="sm"
            />
            </NavLink>

            <div style={{ marginLeft: 16, flexGrow: 1, display: "flex", flexDirection: "column", justifyContent: "space-between" }}>
              <Text
                fw={500}
                lineClamp={1}
                onClick={() => navigate(paths.articulos.view(detalle.articuloId))}
                style={{ cursor: "pointer" }}
              >
                {detalle.nombre}
              </Text>
              <Text fw={700} mt="auto">
                ${detalle.precio.toFixed(2)}
              </Text>
            </div>
            </Group>
          </Card>
        ))}

        <Text fw={700} align="right" size="lg" mt={24}>
          Total: ${total.toFixed(2)}
        </Text>
      </Grid.Col>
    </Grid>
  );
}