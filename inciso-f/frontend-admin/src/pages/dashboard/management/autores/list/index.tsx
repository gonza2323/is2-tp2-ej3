import { Grid } from '@mantine/core';
import { Page } from '@/components/page';
import { PageHeader } from '@/components/page-header';
import { paths } from '@/routes';
import { AutorMetrics } from './autores-metrics';
import { AutoresTable } from './autores-table';

const breadcrumbs = [
  { label: 'Dashboard', href: paths.dashboard.root },
  { label: 'Management', href: paths.dashboard.management.root },
  { label: 'Autores', href: paths.dashboard.management.autores.root },
  { label: 'List' },
];

export default function ListAutoresPage() {
  return (
    <Page title="List autores">
      <PageHeader title="List autores" breadcrumbs={breadcrumbs} />

      <Grid>
        <Grid.Col span={12}>
          <AutorMetrics />
        </Grid.Col>

        <Grid.Col span={12}>
          <AutoresTable />
        </Grid.Col>
      </Grid>
    </Page>
  );
}
