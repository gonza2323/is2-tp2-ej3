
export const paths = {
  
  root: '/',
  home: '/',
  shop: '/shop',
  cart: '/cart',

  articulos: {
    view: (articuloId) => `/articulos/${articuloId}`,
  },

  auth: {
    root: '/auth',
    login: '/auth/login',
    register: '/auth/register',
    forgotPassword: '/auth/forgot-password',
    resetPassword: '/auth/reset-password',
    otp: '/auth/otp',
    terms: '/auth/terms',
    privacy: '/auth/privacy',
  },

  dashboard: {
    root: '/dashboard',
    home: '/dashboard/home',
    management: {
      root: '/dashboard/management',
      proveedores: {
        root: '/dashboard/management/proveedores',
        list: '/dashboard/management/proveedores/list',
        view: (proveedorId) => `/dashboard/management/proveedores/${proveedorId}`,
        edit: (proveedorId) => `/dashboard/management/proveedores/${proveedorId}/edit`,
        add: '/dashboard/management/proveedores/add',
      },
      articulos: {
        root: '/dashboard/management/articulos',
        list: '/dashboard/management/articulos/list',
        view: (articuloId) => `/dashboard/management/articulos/${articuloId}`,
        edit: (articuloId) => `/dashboard/management/articulos/${articuloId}/edit`,
        add: '/dashboard/management/articulos/add',
      },
      autores: {
        root: '/dashboard/management/autores',
        list: '/dashboard/management/autores/list',
        view: (autorId) => `/dashboard/management/autores/${autorId}`,
      },
      customers: {
        root: '/dashboard/management/customers',
        list: '/dashboard/management/customers/list',
        view: (customerId) => `/dashboard/management/customers/${customerId}`,
      },
    },
    apps: {
      root: '/dashboard/apps',
      kanban: '/dashboard/apps/kanban',
    },
    widgets: {
      root: '/dashboard/widgets',
      metrics: '/dashboard/widgets/metrics',
      charts: '/dashboard/widgets/charts',
      tables: '/dashboard/widgets/tables',
    },
  },
};
