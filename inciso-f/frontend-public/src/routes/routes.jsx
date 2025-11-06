import { SingleProduct } from "../pages/SingleProduct";
import { Routes, Route } from "react-router-dom";
import { Home } from "../pages/Home";
import { Shop } from "../pages/Shop/";
import { NotFound } from "../pages/404";
import { HeaderAndFooterLayout } from "../layouts/header-and-footer/header-and-footer";
import { paths } from "./paths";
import LoginPage from "../pages/auth/login/login-page.jsx";
import { AuthLayout } from "../layouts/auth/auth-layout.jsx";
import { GuestGuard } from "../guards/guest-guard";
import { AuthGuard } from "../guards/auth-guard.jsx";
import { CartPage } from "../pages/Cart/index.jsx";

export function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<HeaderAndFooterLayout />}>
                <Route index element={<Home />} />
                <Route path={paths.shop} element={<Shop />}>
                    <Route index />
                    <Route path="category/:category" />
                </Route>
                <Route path="articulos/:id" element={<SingleProduct />} />
                
                <Route path={paths.cart} element={<AuthGuard><CartPage /></AuthGuard>} />

                <Route path="*" element={<NotFound />} />
            </Route>

            <Route path="/auth" element={<GuestGuard><AuthLayout /></GuestGuard>}>
                <Route path={paths.auth.login} element={<LoginPage />} />
            </Route>
        </Routes>
    );
}
