import { Grid } from '@mantine/core';
import { Page } from '@/components/page';
import { PageHeader } from '@/components/page-header';
import { paths } from '@/routes';
import { ProveedoresMap } from './proveedores-table';

const breadcrumbs = [
  { label: 'Dashboard', href: paths.dashboard.root },
  { label: 'Management', href: paths.dashboard.management.root },
  { label: 'Proveedores', href: paths.dashboard.management.proveedores.root },
  { label: 'Mapa' },
];

export default function MapProveedoresPage() {
  return (
    <Page title="Mapa proveedores">
      <PageHeader title="Mapa proveedores" breadcrumbs={breadcrumbs} />

      <Grid>
        {/* <Grid.Col span={12}>
          <ProveedorMetrics />
        </Grid.Col> */}

        <Grid.Col span={12}>
          <ProveedoresMap />
        </Grid.Col>
      </Grid>
    </Page>
  );
}
