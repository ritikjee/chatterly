import InfoBar from "@/components/global/infobar";
import Sidebar from "@/components/global/sidebar";
import { AutomationService } from "@/services/automation-service";
import { UserService } from "@/services/user-service";
import {
  dehydrate,
  HydrationBoundary,
  QueryClient,
} from "@tanstack/react-query";

type Props = {
  children: React.ReactNode;
  params: { slug: string };
};

const Layout = async ({ children, params }: Props) => {
  const query = new QueryClient();

  await query.prefetchQuery({
    queryKey: ["user-profile"],
    queryFn: () => UserService.getAuthenticatedUser(),
  });

  await query.prefetchQuery({
    queryKey: ["user-automations"],
    queryFn: () => AutomationService.getAutomations(),
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
