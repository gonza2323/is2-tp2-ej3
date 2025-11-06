import { usePagination } from '../../api/helpers';
import { AddButton } from '../../components/add-button';
import { useGetArticulos, useDeleteArticulo } from '../../hooks';
import { paths } from '../../routes/paths';
import { formatCurrency } from '../../utilities/number';
import { useNavigate } from 'react-router-dom';
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
} from '@mantine/core';
import { app } from '../../config';

export function Shop() {
  const navigate = useNavigate();
  const { page, size, setPage, setSize } = usePagination();

  const [sortValue, setSortValue] = useState("nombre,asc");

  const { data, isLoading } = useGetArticulos({
    query: {
      page,
      size,
      sort: sortValue,
    },
  });

  const articulos = data?.data ?? [];
  const totalPages = Math.ceil((data?.meta.totalElements ?? 0) / size);

  return (
    <Container size="lg" py="md">
      <Group position="right" spacing="md" mb="lg">
        <Select
          value={size.toString()}
          onChange={(val) => setSize(Number(val))}
          data={["5", "15", "30"]}
          label="Items per page"
          size="sm"
        />
        <Select
          value={sortValue}
          onChange={(val) => setSortValue(val)}
          data={[
            { value: "nombre,asc", label: "Name A-Z" },
            { value: "nombre,desc", label: "Name Z-A" },
            { value: "precio,asc", label: "Price Low-High" },
            { value: "precio,desc", label: "Price High-Low" },
          ]}
          label="Sort by"
          size="sm"
        />
      </Group>

      {isLoading ? (
        <Text align="center" mt="xl">
          Loading...
        </Text>
      ) : articulos.length === 0 ? (
        <Text align="center" mt="xl">
          No articulos found
        </Text>
      ) : (
        <Grid gutter="lg">
          {articulos.map((articulo) => (
            <Grid.Col
              key={articulo.id}
              span={{ base: 12, sm: 6, md: 4, lg: 3 }}
              style={{ display: "flex" }}
            >
              <Card
                shadow="sm"
                padding="md"
                radius="md"
                withBorder
                style={{
                  cursor: "pointer",
                  display: "flex",
                  flexDirection: "column",
                  width: "100%"
                }}
                onClick={() => navigate(paths.articulos.view(articulo.id))}
              >
                <Card.Section mb="sm">
                  <Image
                    src={`${app.apiBaseUrl}/imagenes/${articulo.imagenId}`}
                    alt={articulo.nombre}
                    fit="cover"

                  />
                </Card.Section>

                <div style={{ flexGrow: 1, display: "flex", flexDirection: "column", justifyContent: "flex-end" }}>
                  <Group>
                    <Stack>
                      <Text fw={500} lineClamp={1}>
                        {articulo.nombre}
                      </Text>
                      <Text fw={700} mt="xs">
                        ${articulo.precio.toFixed(2)}
                      </Text>
                    </Stack>
                  </Group>
                </div>
              </Card>
            </Grid.Col>
          ))}
        </Grid>
      )}

      {totalPages > 1 && (
        <Pagination
          mt="xl"
          align="center"
          page={page + 1}
          onChange={(p) => setPage(p - 1)}
          total={totalPages}
        />
      )}
    </Container>
  );
}
