import { Grid } from '@mantine/core';
import { Page } from '@/components/page';
import { PageHeader } from '@/components/page-header';
import { paths } from '@/routes';
import { ProveedoresTable } from './proveedores-table';

const breadcrumbs = [
  { label: 'Dashboard', href: paths.dashboard.root },
  { label: 'Management', href: paths.dashboard.management.root },
  { label: 'Proveedores', href: paths.dashboard.management.proveedores.root },
  { label: 'Lista' },
];

export default function ListProveedoresPage() {
  return (
    <Page title="Lista proveedores">
      <PageHeader title="Lista proveedores" breadcrumbs={breadcrumbs} />

      <Grid>
        {/* <Grid.Col span={12}>
          <ProveedorMetrics />
        </Grid.Col> */}

        <Grid.Col span={12}>
          <ProveedoresTable />
        </Grid.Col>
      </Grid>
    </Page>
  );
}
