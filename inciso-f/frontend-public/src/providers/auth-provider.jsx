import { createContext, ReactNode, useEffect, useMemo, useState } from 'react';
import { loadAccessToken, removeClientAccessToken } from '../api/axios';
import { getAccountInfo } from '../api/resources';

export const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isInitialized, setIsInitialized] = useState(false);

  useEffect(() => {
    loadAccessToken();

    getAccountInfo()
      .then(() => setIsAuthenticated(true))
      .catch(() => {setIsAuthenticated(false); removeClientAccessToken()})
      .finally(() => setIsInitialized(true));
  }, []);

  const value = useMemo(
    () => ({ isAuthenticated, isInitialized, setIsAuthenticated }),
    [isAuthenticated, isInitialized]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
