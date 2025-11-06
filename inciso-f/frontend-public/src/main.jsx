import '@mantine/core/styles.layer.css';
import './global.css';

import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { BrowserRouter, HashRouter } from "react-router-dom";
import { CartProvider } from "./context/CartContext.jsx";
import { MantineProvider } from "@mantine/core";
import { queryClient } from "./api/query-client.js";
import { QueryClientProvider } from "@tanstack/react-query";
import { theme } from "./theme";
import { Notifications } from '@mantine/notifications';
import { ModalsProvider } from '@mantine/modals';

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <MantineProvider theme={theme} defaultColorScheme='light'>
        <Notifications position="bottom-center" />
        <ModalsProvider>
          <BrowserRouter>
            <App />
          </BrowserRouter>
        </ModalsProvider>
      </MantineProvider>
    </QueryClientProvider>
  </React.StrictMode>
);
