import { UserService } from "@/services/user-service";
import { redirect } from "next/navigation";

async function DashboardPage() {
  const { data } = await UserService.getAuthenticatedUser();

  if (data) {
    return redirect(`dashboard/${data?.firstname}${data?.lastname}`);
  }

  return redirect("/auth/sign-in");
}

export default DashboardPage;
