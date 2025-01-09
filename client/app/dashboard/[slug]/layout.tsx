import {
  dehydrate,
  HydrationBoundary,
  QueryClient,
} from "@tanstack/react-query";
import InfoBar from "@/components/global/infobar";
import Sidebar from "@/components/global/sidebar";
import { UserService } from "@/services/user-service";
import { cookies } from "next/headers";

type Props = {
  children: React.ReactNode;
  params: { slug: string };
};

const Layout = async ({ children, params }: Props) => {
  const query = new QueryClient();

  const cookieStore = cookies();
  const token = cookieStore.get("token")?.value;

  await query.prefetchQuery({
    queryKey: ["user-profile"],
    queryFn: () => UserService.getAuthenticatedUser(token),
    staleTime: 60000,
  });

  return (
    <HydrationBoundary state={dehydrate(query)}>
      <div className="p-3">
        <Sidebar slug={params.slug} />
        <div
          className="
                  lg:ml-[250px] 
                  lg:pl-10 
                  lg:py-5 
                  flex 
                  flex-col 
                  overflow-auto
                  "
        >
          <InfoBar slug={params.slug} />
          {children}
        </div>
      </div>
    </HydrationBoundary>
  );
};

export default Layout;
