import { Route, Routes } from "react-router";
import { Home } from "../pages/Home/Home";
import { About } from "../pages/About/About";
import { NotFound } from "../pages/NotFound/NotFound";

export function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}