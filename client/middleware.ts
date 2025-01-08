import { NextRequest, NextResponse } from "next/server";

export async function middleware(req: NextRequest) {
  const backendUrl = process.env.NEXT_PUBLIC_BACKEND_URL;
  const publicRoutes: Array<string> = ["/auth/sign-in", "/auth/sign-up"];
  const { pathname } = req.nextUrl;

  const cookie = req.cookies.get("token")?.value;

  // If token exists, validate it with the backend
  if (cookie) {
    const response = await fetch(`${backendUrl}/api/v1/user/me`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Cookie: `token=${cookie}`,
      },
    });

    // If token is valid
    if (response.ok) {
      // Redirect to dashboard if trying to access public routes
      if (publicRoutes.includes(pathname)) {
        return NextResponse.redirect(new URL("/dashboard", req.url));
      }

      // Allow access to other routes
      return NextResponse.next();
    }
  }

  // If token is invalid or missing
  if (publicRoutes.includes(pathname)) {
    // Allow access to public routes
    return NextResponse.next();
  }

  // Redirect to sign-in for protected routes
  return NextResponse.redirect(new URL("/auth/sign-in", req.url));
}

export const config = {
  matcher: ["/:path*"],
};
