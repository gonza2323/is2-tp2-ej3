import { Outlet } from "react-router-dom";
import { Footer } from "../../components/Footer";
import { Header } from "../../components/Header";


export function HeaderAndFooterLayout() {
    return (
        <>
            <Header />
            <Outlet />
            <Footer />
        </>
    )
}