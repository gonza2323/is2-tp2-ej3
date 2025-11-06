import { ElementType } from 'react';
import {
  PiChartLineUpDuotone,
  PiChatCenteredDotsDuotone,
  PiFilesDuotone,
  PiKanbanDuotone,
  PiLockKeyDuotone,
  PiShieldCheckDuotone,
  PiShoppingCart,
  PiSquaresFourDuotone,
  PiStarDuotone,
  PiTableDuotone,
  PiTruck,
  PiUserPlusDuotone,
  PiUsersDuotone,
} from 'react-icons/pi';
import { paths } from '@/routes/paths';

interface MenuItem {
  header: string;
  section: {
    name: string;
    href: string;
    icon: ElementType;
    dropdownItems?: {
      name: string;
      href: string;
      badge?: string;
    }[];
  }[];
}

export const menu: MenuItem[] = [
  {
    header: 'Overview',
    section: [
      {
        name: 'Welcome',
        href: paths.dashboard.home,
        icon: PiStarDuotone,
      },
    ],
  },

  {
    header: 'Management',
    section: [
      {
        name: 'Proveedores',
        icon: PiTruck,
        href: paths.dashboard.management.proveedores.root,
        dropdownItems: [
          {
            name: 'List',
            href: paths.dashboard.management.proveedores.list,
          },
          {
            name: 'Map',
            href: paths.dashboard.management.proveedores.map,
          },
        ],
      },
      {
        name: 'Artículos',
        icon: PiShoppingCart,
        href: paths.dashboard.management.articulos.root,
        dropdownItems: [
          {
            name: 'List',
            href: paths.dashboard.management.articulos.list,
          },
        ],
      },
      {
        name: 'Autores',
        icon: PiUsersDuotone,
        href: paths.dashboard.management.autores.root,
        dropdownItems: [
          {
            name: 'List',
            href: paths.dashboard.management.autores.list,
          },
        ],
      },
      {
        name: 'Customers',
        icon: PiUsersDuotone,
        href: paths.dashboard.management.customers.root,
        dropdownItems: [
          {
            name: 'List',
            href: paths.dashboard.management.customers.list,
          },
        ],
      },
    ],
  },

  {
    header: 'Apps',
    section: [
      {
        name: 'Kanban',
        href: paths.dashboard.apps.kanban,
        icon: PiKanbanDuotone,
      },
    ],
  },

  {
    header: 'Widgets',
    section: [
      {
        name: 'Charts',
        href: paths.dashboard.widgets.charts,
        icon: PiChartLineUpDuotone,
      },
      {
        name: 'Metrics',
        href: paths.dashboard.widgets.metrics,
        icon: PiSquaresFourDuotone,
      },
      {
        name: 'Tables',
        href: paths.dashboard.widgets.tables,
        icon: PiTableDuotone,
      },
    ],
  },

  {
    header: 'Documentation',
    section: [
      {
        name: 'Documentation',
        href: paths.docs.root,
        icon: PiFilesDuotone,
      },
    ],
  },

  {
    header: 'Authentication',
    section: [
      {
        name: 'Register',
        href: paths.auth.register,
        icon: PiUserPlusDuotone,
      },
      {
        name: 'Login',
        href: paths.auth.login,
        icon: PiShieldCheckDuotone,
      },
      {
        name: 'Forgot Password',
        href: paths.auth.forgotPassword,
        icon: PiLockKeyDuotone,
      },
      {
        name: 'OTP',
        href: paths.auth.otp,
        icon: PiChatCenteredDotsDuotone,
      },
    ],
  },
];
