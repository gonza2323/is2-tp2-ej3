import { useGetArticulo } from '../../hooks';
import { paths } from '../../routes/paths';
import { formatCurrency } from '../../utilities/number';
import { useNavigate, useParams } from 'react-router-dom';
import { useState } from 'react';
import {
  Card,
  Image,
  Stack,
  Group,
  Text,
  Button,
  NumberInput,
  Alert,
  Loader,
  Container,
} from '@mantine/core';
import { FaShoppingCart, FaArrowLeft } from 'react-icons/fa';
import { app } from '../../config';
import { useAddArticulo } from '../../hooks/api/carrito';

export function SingleProduct() {
  const { id } = useParams();
  const navigate = useNavigate();

  const { data: articulo, isLoading, error } = useGetArticulo({
    route: { id: id }
  });

  const addToCartMutation = useAddArticulo();

  const handleAddToCart = () => {
    addToCartMutation.mutate({
      variables: undefined,
      route: { id },
    });
  };

  if (isLoading) {
    return (
      <Container size="sm" py="xl">
        <Loader />
      </Container>
    );
  }

  if (error) {
    return (
      <Container size="sm" py="xl">
        <Alert color="red">Error al cargar el producto</Alert>
      </Container>
    );
  }

  const imageUrl = articulo?.imagenId
    ? `${app.apiBaseUrl}/imagenes/${articulo.imagenId}`
    : null;

  return (
    <Container size="sm" py="xl">
      <Button
        variant="subtle"
        leftSection={<FaArrowLeft size={18} />}
        onClick={() => navigate(paths.shop)}
      >
        Volver a la tienda
      </Button>
      <Card shadow="sm" padding="lg">
        <Stack gap="md">
          {imageUrl && (
            <Image
              src={imageUrl}
              alt={`Imagen de ${articulo?.nombre}`}
              radius="md"
              fit="contain"
              height={300}
            />
          )}

          <Text size="xl" fw={700}>
            {articulo?.nombre || "-"}
          </Text>

          <Text size="lg" fw={600} c="blue">
            {articulo ? formatCurrency(articulo.precio) : "-"}
          </Text>

          <Group align="flex-end" gap="md">
            <Button
              leftSection={<FaShoppingCart size={18} />}
              onClick={handleAddToCart}
              size="md"
            >
              Agregar al carrito
            </Button>
          </Group>
        </Stack>

        <Group justify="space-between" mt="xl">

        </Group>
      </Card>
    </Container>
  );
}