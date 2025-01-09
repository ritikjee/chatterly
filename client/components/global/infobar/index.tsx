"use client";

import { Menu } from "lucide-react";
import { Separator } from "@/components/ui/separator";
import { HelpDuoToneWhite } from "@/icons";
import { usePaths } from "@/hooks/use-nav";
import { PAGE_BREAD_CRUMBS } from "@/constants/page";
import Items from "@/components/global/sidebar/item";
import { SubscriptionPlan } from "@/components/global/user-auth-button";
import UpgradeCard from "@/components/global/sidebar/upgrade";
import Sheet from "@/components/global/sheet";
import Image from "next/image";
import MainBreadCrumb from "@/components/global/main-bread-crump";
import { ScrollArea } from "@/components/ui/scroll-area";
import Search from "../search";
import { Notifications } from "../notification";
import CreateAutomation from "../create-automation-button";

type Props = {
  slug: string;
};

const InfoBar = ({ slug }: Props) => {
  const { page } = usePaths();
  const currentPage = PAGE_BREAD_CRUMBS.includes(page) || page == slug;

  return (
    currentPage && (
      <div className="flex flex-col">
        <div className="flex gap-x-3 lg:gap-x-5 justify-end">
          <span className="lg:hidden flex items-center flex-1 gap-x-2">
            <Sheet trigger={<Menu />} className="lg:hidden" side="left">
              <ScrollArea
                className="flex flex-col 
                            gap-y-5
                            w-full 
                            h-full 
                            p-3 
                            bg-[#0e0e0e] 
                            bg-opacity-90 
                            bg-clip-padding 
                            backdrop-filter 
                            backdrop--blur__safari 
                            backdrop-blur-3xl
                            rounded-3xl
                            "
              >
                <div className="flex gap-x-2 items-center p-5 justify-center">
                  <Image
                    src="/logo-small.png"
                    width={150}
                    height={50}
                    alt="logo"
                  />
                </div>
                <div className="flex flex-col py-3">
                  <Items page={page} slug={slug} />
                </div>
                <div className="px-16">
                  <Separator
                    orientation="horizontal"
                    className="bg-[#333336]"
                  />
                </div>
                <div className="px-3 flex flex-col gap-y-5">
                  <div className="flex gap-x-2">
                    {/* WIP: Implement device features */}
                    <p className="text-[#9B9CA0]">Profile</p>
                  </div>
                  <div className="flex gap-x-3 pb-10">
                    <HelpDuoToneWhite />
                    <p className="text-[#9B9CA0]">Help</p>
                  </div>
                </div>
                <SubscriptionPlan type="FREE">
                  <div className="flex-1 flex flex-col justify-end">
                    <UpgradeCard />
                  </div>
                </SubscriptionPlan>
              </ScrollArea>
            </Sheet>
          </span>
          <Search />
          <CreateAutomation />
          <Notifications />
        </div>
        <MainBreadCrumb page={page === slug ? "Home" : page} slug={slug} />
      </div>
    )
  );
};

export default InfoBar;
