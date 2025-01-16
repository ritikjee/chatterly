import AutomationsBreadCrumb from "@/components/global/bread-crumbs/automation";
import PostNode from "@/components/global/post/node";
import ThenNode from "@/components/global/then/node";
import Trigger from "@/components/global/trigger";
import { Warning } from "@/icons";
import { AutomationService } from "@/services/automation-service";
import { SubscriptionService } from "@/services/subscription-service";
import {
  dehydrate,
  HydrationBoundary,
  QueryClient,
} from "@tanstack/react-query";
import React from "react";

type Props = { params: { id: string } };

export async function generateMetadata({ params }: { params: { id: string } }) {
  const { data } = await AutomationService.getAutomationById(params.id);

  return {
    title: data?.name + " | Automation",
  };
}

async function Page({ params }: Props) {
  const query = new QueryClient();

  await query.prefetchQuery({
    queryKey: ["automation-info", params.id],
    queryFn: () => AutomationService.getAutomationById(params.id),
  });

  await query.prefetchQuery({
    queryKey: ["user-subscription"],
    queryFn: () => SubscriptionService.getSubscription(),
  });

  return (
    <HydrationBoundary state={dehydrate(query)}>
      <div className=" flex flex-col items-center gap-y-20">
        <AutomationsBreadCrumb id={params.id} />
        <div className="w-full lg:w-10/12 xl:w-6/12 p-5 rounded-xl flex flex-col bg-[#1D1D1D] gap-y-3">
          <div className="flex gap-x-2">
            <Warning />
            When...
          </div>
          <Trigger id={params.id} />
        </div>
        <ThenNode id={params.id} />
        <PostNode id={params.id} />
      </div>
    </HydrationBoundary>
  );
}

export default Page;
