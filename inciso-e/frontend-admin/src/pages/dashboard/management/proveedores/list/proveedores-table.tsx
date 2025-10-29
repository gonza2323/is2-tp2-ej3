import { useEffect, useMemo } from 'react';
import { DataTableColumn } from 'mantine-datatable';
import { Avatar, Box, Group, Loader, Rating, Text } from '@mantine/core';
import { ProveedorSummaryDto } from '@/api/dtos';
import { usePagination } from '@/api/helpers';
import { AddButton } from '@/components/add-button';
import { DataTable } from '@/components/data-table';
import { LinkChip } from '@/components/link-chip';
import { useGetProveedores, useDeleteProveedor } from '@/hooks';
import { paths } from '@/routes';
import { formatDate } from '@/utilities/date';
import { formatPhoneNumber } from '@/utilities/phone-number';
import { firstLetters } from '@/utilities/text';
import { NavLink, useNavigate } from 'react-router-dom';
import { modals } from '@mantine/modals';
import { notifications } from '@mantine/notifications';

type SortableFields = Pick<ProveedorSummaryDto, 'nombre'>;

export function ProveedoresTable() {
  const navigate = useNavigate();
  const { page, size, setSize: setSize, setPage } = usePagination();
  // const { tabs, filters, sort } = DataTable.useDataTable<SortableFields>({
  const { sort } = DataTable.useDataTable<SortableFields>({
    sortConfig: {
      direction: 'asc',
      column: 'nombre',
    },
  });

  const { data, isLoading } = useGetProveedores({
    query: {
      page,
      size,
      // status: tabs.value as Proveedor['status'],
      sort: sort.query,
    },
  });

  useEffect(() => {
    const totalPages = data?.meta?.totalPages;
    if (totalPages != null && page != null) {
      if (page >= totalPages) {
        setPage(Math.max(page - 1, 0));
      }
    }
  }, [data, page, setPage]);

  const deleteMutation = useDeleteProveedor();

  const columns: DataTableColumn<ProveedorSummaryDto>[] = useMemo(
    () => [
      {
        accessor: 'nombre',
        title: 'Nombre',
        sortable: true,
        render: (proveedor) => (
          <Text truncate="end">{proveedor.nombre}</Text>
        ),
      },
      {
        accessor: 'actions',
        title: 'Acciones',
        textAlign: 'right',
        width: 100,
        render: (proveedor) => (
          <DataTable.Actions
            onView={() => { navigate(paths.dashboard.management.proveedores.view(proveedor.id)) }}
            onEdit={() => { navigate(paths.dashboard.management.proveedores.edit(proveedor.id)) }}
            onDelete={() => handleDelete(proveedor)}
          />
        ),
      },
    ],
    []
  );

  const handleDelete = (proveedor: ProveedorSummaryDto) => {
    modals.openConfirmModal({
      title: 'Confirmar borrado',
      children: <Text>¿Está seguro de que desea borrar el proveedor?</Text>,
      labels: { confirm: 'Delete', cancel: 'Cancel' },
      confirmProps: { color: 'red' },
      onConfirm: () => {
        deleteMutation.mutate({
          model: proveedor,
          route: { id: proveedor.id },
        });
      },
    });
  };

  return (
    <DataTable.Container>
      <DataTable.Title
        title="Proveedores"
        description="Lista de proveedores"
        actions={
          <AddButton variant="default" size="xs" component={NavLink} to={paths.dashboard.management.proveedores.add}>
            Agregar proveedor
          </AddButton>
        }
      />

      {/* <DataTable.Tabs tabs={tabs.tabs} onChange={tabs.change} /> */}
      {/* <DataTable.Filters filters={filters.filters} onClear={filters.clear} /> */}
      <DataTable.Content>
        <DataTable.Table
          minHeight={240}
          noRecordsText={DataTable.noRecordsText('proveedor')}
          recordsPerPageLabel={DataTable.recordsPerPageLabel('proveedores')}
          paginationText={DataTable.paginationText('proveedores')}
          page={page + 1}
          records={data?.data ?? []}
          fetching={isLoading}
          onPageChange={(pageNo) => setPage(pageNo - 1)}
          recordsPerPage={size}
          totalRecords={data?.meta.totalElements ?? 0}
          onRecordsPerPageChange={setSize}
          recordsPerPageOptions={[5, 15, 30]}
          sortStatus={sort.status}
          onSortStatusChange={sort.change}
          columns={columns}
        />
      </DataTable.Content>
    </DataTable.Container>
  );
}
