import { ProveedorSummaryDto } from '@/api/dtos';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import { useGetProveedores, useDeleteProveedor } from '@/hooks';
import { Alert, Card, Loader } from '@mantine/core';

export function ProveedoresMap() {
  const { data, isLoading, error } = useGetProveedores({
    query: {
      size: 99999,
    },
  });
  const defaultCenter = [-34.6037, -58.3816];

  if (isLoading) {
    return (
      <Loader />
    )
  }

  if (error) {
    return (
      <Alert>Error!</Alert>
    )
  }

  return (
    <Card shadow="sm" radius="md" p="md" style={{ height: '500px' }}>
      <MapContainer
        center={defaultCenter}
        zoom={12}
        style={{ height: '100%', width: '100%' }}
      >
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        
        {data.data.map((p) => (
          <Marker key={p.nombre} position={[p.latitud, p.longitud]}>
            <Popup>
              <strong>{p.nombre}</strong><br />
              Lat: {p.latitud.toFixed(4)}<br />
              Lon: {p.longitud.toFixed(4)}
            </Popup>
          </Marker>
        ))}
      </MapContainer>
    </Card>
  );
}
