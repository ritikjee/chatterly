import { UserService } from "@/services/user-service";
import { redirect } from "next/navigation";
import { cookies } from "next/headers";

async function DashboardPage() {
  const cookieStore = cookies();
  const token = cookieStore.get("token")?.value;

  if (!token) {
    return redirect("/auth/sign-in");
  }

  const { data } = await UserService.getAuthenticatedUser(token);

  if (data) {
    return redirect(`dashboard/${data?.firstname}${data?.lastname}`);
  }

  return redirect("/auth/sign-in");
}

export default DashboardPage;
