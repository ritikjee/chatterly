import { Suspense } from "react";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { VerifyAccount } from "./_component/verify-account";

export default function VerifyPage() {
  return (
    <main className="min-h-screen bg-gradient-to-b from-slate-900 via-blue-900 to-bg flex items-center justify-center">
      <div className="absolute inset-0 bg-[linear-gradient(to_right,#0f172a_1px,transparent_1px),linear-gradient(to_bottom,#0f172a_1px,transparent_1px)] bg-[size:4rem_4rem] [mask-image:radial-gradient(ellipse_60%_50%_at_50%_0%,#000_70%,transparent_110%)]" />
      <Card className="w-full max-w-md relative z-10">
        <CardHeader>
          <CardTitle className="text-2xl font-bold text-center">
            Verify Your Account
          </CardTitle>
          <CardDescription className="text-center">
            Please wait while we verify your account
          </CardDescription>
        </CardHeader>
        <CardContent>
          <Suspense fallback={<div>Loading...</div>}>
            <VerifyAccount />
          </Suspense>
        </CardContent>
      </Card>
    </main>
  );
}
