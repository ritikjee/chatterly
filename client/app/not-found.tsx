import Link from "next/link";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";

export default function NotFound() {
  return (
    <main className="min-h-screen bg-gradient-to-b from-slate-900 via-blue-900 to-bg flex items-center justify-center">
      <div className="absolute inset-0 bg-[linear-gradient(to_right,#0f172a_1px,transparent_1px),linear-gradient(to_bottom,#0f172a_1px,transparent_1px)] bg-[size:4rem_4rem] [mask-image:radial-gradient(ellipse_60%_50%_at_50%_0%,#000_70%,transparent_110%)]" />
      <Card className="w-full max-w-md relative z-10">
        <CardHeader>
          <CardTitle className="text-4xl font-bold text-center">404</CardTitle>
          <CardDescription className="text-xl text-center">
            Page Not Found
          </CardDescription>
        </CardHeader>
        <CardContent className="text-center">
          <p className="text-muted-foreground">
            Oops! The page you&apos;re looking for doesn&apos;t exist or has
            been moved.
          </p>
        </CardContent>
        <CardFooter className="flex justify-center">
          <Button asChild className="bg-blue-600 text-white hover:bg-blue-700">
            <Link href="/">Return to Home</Link>
          </Button>
        </CardFooter>
      </Card>
    </main>
  );
}
