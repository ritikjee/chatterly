import { NextRequest, NextResponse } from "next/server";

export async function middleware(req: NextRequest) {
  const backendUrl = process.env.NEXT_PUBLIC_BACKEND_URL;
  const publicRoutes: Array<string> = ["/auth/sign-in", "/auth/sign-up"];
  const { pathname } = req.nextUrl;

  // Allowed paths and file extensions
  const allowedPaths = ["/_next/", "/static/", "/favicon.ico"];
  const allowedExtensions = [".css", ".js", ".png", ".jpg", ".svg"];

  if (
    allowedPaths.some((path) => pathname.startsWith(path)) ||
    allowedExtensions.some((ext) => pathname.endsWith(ext))
  ) {
    return NextResponse.next();
  }

  const cookie = req.cookies.get("token")?.value;

  if (cookie) {
    const response = await fetch(`${backendUrl}/api/v1/user/me`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Cookie: `token=${cookie}`,
      },
    });

    if (response.ok) {
      if (publicRoutes.includes(pathname)) {
        return NextResponse.redirect(new URL("/dashboard", req.url));
      }

      return NextResponse.next();
    }
  }

  if (publicRoutes.includes(pathname)) {
    return NextResponse.next();
  }

  return NextResponse.redirect(new URL("/auth/sign-in", req.url));
}

export const config = {
  matcher: ["/:path*"],
};
