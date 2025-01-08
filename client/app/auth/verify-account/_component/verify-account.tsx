"use client";

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import { Button } from "@/components/ui/button";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { CheckCircle, XCircle } from "lucide-react";
import Link from "next/link";
import { AuthService } from "@/services/auth-service";

export function VerifyAccount() {
  const searchParams = useSearchParams();
  const [verificationStatus, setVerificationStatus] = useState<
    "loading" | "success" | "error"
  >("loading");
  const [errorMessage, setErrorMessage] = useState("");

  const router = useRouter();

  useEffect(() => {
    const email = searchParams.get("email");
    const token = searchParams.get("token");

    if (!email || !token) {
      setVerificationStatus("error");
      setErrorMessage(
        "Invalid verification link. Please check your email and try again."
      );
      return;
    }

    const verifyAccount = async () => {
      const { error } = await AuthService.verifyAccount({ email, token });
      if (error) {
        setVerificationStatus("error");
        setErrorMessage(error.message);
        return;
      } else {
        setVerificationStatus("success");

        setTimeout(() => {
          router.push("/auth/sign-in");
        }, 3000);
      }
    };

    verifyAccount();
  }, [searchParams]);

  if (verificationStatus === "loading") {
    return <div className="text-center">Verifying your account...</div>;
  }

  if (verificationStatus === "success") {
    return (
      <Alert className="bg-green-100 border-green-500">
        <CheckCircle className="h-4 w-4 text-green-500" />
        <AlertTitle>Success!</AlertTitle>
        <AlertDescription>
          Your account has been successfully verified. You can now log in.
        </AlertDescription>
        <Button
          asChild
          className="mt-4 w-full bg-blue-600 text-white hover:bg-blue-700"
        >
          <Link href="/login">Go to Login</Link>
        </Button>
      </Alert>
    );
  }

  if (verificationStatus === "error") {
    return (
      <Alert className="bg-red-100 border-red-500">
        <XCircle className="h-4 w-4 text-red-500" />
        <AlertTitle>Verification Failed</AlertTitle>
        <AlertDescription>{errorMessage}</AlertDescription>
        <Button
          asChild
          className="mt-4 w-full bg-blue-600 text-white hover:bg-blue-700"
        >
          <Link href="/signup">Back to Sign Up</Link>
        </Button>
      </Alert>
    );
  }

  return null;
}
