import { BrowserRouter } from 'react-router'
import '@mantine/core/styles.css';
import { MantineProvider } from '@mantine/core';
import { AppRoutes } from './routes/AppRoutes';

function App() {
  return (
    <BrowserRouter>
      <MantineProvider withGlobalStyles withNormalizeCSS defaultColorScheme="dark">
        <AppRoutes />
      </MantineProvider>
    </BrowserRouter>
  );
}

export { App }