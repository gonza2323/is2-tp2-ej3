import { useMemo } from 'react';
import { DataTableColumn } from 'mantine-datatable';
import { Avatar, Box, Group, Loader, Rating, Text } from '@mantine/core';
import { Autor } from '@/api/entities';
import { usePagination } from '@/api/helpers';
import { AddButton } from '@/components/add-button';
import { DataTable } from '@/components/data-table';
import { LinkChip } from '@/components/link-chip';
import { AutorStatusBadge } from '@/components/resources/autores';
import { useGetAutores, useGetAutoresMetrics } from '@/hooks';
import { paths } from '@/routes';
import { formatDate } from '@/utilities/date';
import { formatPhoneNumber } from '@/utilities/phone-number';
import { firstLetters } from '@/utilities/text';

type SortableFields = Pick<Autor, 'fullName' | 'rating' | 'createdAt'>;

export function AutoresTable() {
  // const { data: metrics } = useGetAutoresMetrics();
  const { page, size, setSize: setSize, setPage } = usePagination();
  // const { tabs, filters, sort } = DataTable.useDataTable<SortableFields>({
  const { filters, sort } = DataTable.useDataTable<SortableFields>({
    sortConfig: {
      direction: 'asc',
      column: 'fullName',
    },
  });

  const { data, isLoading } = useGetAutores({
    query: {
      page,
      size,
      // status: tabs.value as Autor['status'],
      sort: sort.query,
    },
  });

  const columns: DataTableColumn<Autor>[] = useMemo(
    () => [
      {
        accessor: 'number',
        title: 'Autor n°',
        width: 156,
        render: (autor) => (
          <LinkChip href={paths.dashboard.management.autores.view(autor.id)}>
            {autor.number}
          </LinkChip>
        ),
      },
      {
        accessor: 'fullName',
        title: 'Name',
        sortable: true,
        render: (autor) => (
          <Group wrap="nowrap">
            <Avatar src={autor.avatarUrl} alt={autor.displayName}>
              {firstLetters(autor.displayName)}
            </Avatar>
            <Box w="16rem">
              <Text truncate="end">{autor.fullName}</Text>
              <Text size="sm" c="dimmed" truncate="end">
                {autor.email}
              </Text>
            </Box>
          </Group>
        ),
      },
      {
        accessor: 'phoneNumber',
        title: 'Phone number',
        noWrap: true,
        width: 180,
        render: (autor) => formatPhoneNumber(autor.phoneNumber),
      },
      {
        accessor: 'rating',
        title: 'Rating',
        width: 160,
        sortable: true,
        render: (autor) => <Rating value={autor.rating} fractions={2} readOnly />,
      },
      {
        accessor: 'status',
        title: 'Status',
        width: 120,
        render: (autor) => <AutorStatusBadge status={autor.status} w="100%" />,
      },
      {
        accessor: 'createdAt',
        title: 'Created at',
        noWrap: true,
        width: 140,
        sortable: true,
        render: (autor) => formatDate(autor.createdAt),
      },
      {
        accessor: 'updatedAt',
        title: 'Updated at',
        noWrap: true,
        width: 140,
        render: (autor) => formatDate(autor.updatedAt),
      },
      {
        accessor: 'actions',
        title: 'Actions',
        textAlign: 'right',
        width: 100,
        render: () => (
          <DataTable.Actions onView={console.log} onEdit={console.log} onDelete={console.log} />
        ),
      },
    ],
    []
  );

  return (
    <DataTable.Container>
      <DataTable.Title
        title="Autores"
        description="Lista de autores"
        actions={
          <AddButton variant="default" size="xs">
            Agregar autor
          </AddButton>
        }
      />
      
      {/* <DataTable.Tabs tabs={tabs.tabs} onChange={tabs.change} /> */}
      {/* <DataTable.Filters filters={filters.filters} onClear={filters.clear} /> */}
      <DataTable.Content>
        <DataTable.Table
          minHeight={240}
          noRecordsText={DataTable.noRecordsText('autor')}
          recordsPerPageLabel={DataTable.recordsPerPageLabel('autores')}
          paginationText={DataTable.paginationText('autores')}
          page={page}
          records={data?.data ?? []}
          fetching={isLoading}
          onPageChange={setPage}
          recordsPerPage={size}
          totalRecords={data?.meta.total ?? 0}
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
