import { Page } from '@/components/page';
import { PageHeader } from '@/components/page-header';
import { paths } from '@/routes';
import ProveedorCreateForm from './proveedor-create-form';

const breadcrumbs = [
  { label: 'Dashboard', href: paths.dashboard.root },
  { label: 'Management', href: paths.dashboard.management.root },
  { label: 'Proveedores', href: paths.dashboard.management.proveedores.root },
  { label: 'Nuevo Proveedor' },
];

export default function ProveedorCreatePage() {
  return (
    <Page title="Nuevo proveedor">
      <PageHeader title="Nuevo proveedor" breadcrumbs={breadcrumbs} />
      <ProveedorCreateForm />
    </Page>
  );
}
