import { useMemo } from 'react';
import { DataTableColumn } from 'mantine-datatable';
import { Avatar, Box, Group, Loader, Rating, Text } from '@mantine/core';
import { Proveedor } from '@/api/entities';
import { usePagination } from '@/api/helpers';
import { AddButton } from '@/components/add-button';
import { DataTable } from '@/components/data-table';
import { LinkChip } from '@/components/link-chip';
import { useGetProveedores } from '@/hooks';
import { paths } from '@/routes';
import { formatDate } from '@/utilities/date';
import { formatPhoneNumber } from '@/utilities/phone-number';
import { firstLetters } from '@/utilities/text';
import { useNavigate } from 'react-router-dom';

type SortableFields = Pick<Proveedor, 'nombre'>;

export function ProveedoresTable() {
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

  const columns: DataTableColumn<Proveedor>[] = useMemo(
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
          <DataTable.Actions onView={() => console.log(proveedor.id)} onEdit={() => console.log(proveedor.id)} onDelete={() => console.log(proveedor.id)} />
        ),
      },
    ],
    []
  );

  return (
    <DataTable.Container>
      <DataTable.Title
        title="Proveedores"
        description="Lista de proveedores"
        actions={
          <AddButton variant="default" size="xs">
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
