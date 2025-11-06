import { AppRoutes } from "./routes/routes";
import { AuthProvider } from "./providers/auth-provider";

function App() {
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  );
}

export default App;
