import { Listeners, MediaType } from "@/enums/automation";

export type Automation = {
  id: string;
  name: string;
  createdAt: string; // ISO 8601 format date string
  active: boolean;
  triggers: Trigger[];
  listener: Listener | null;
  posts: Post[];
  dms: Dms[];
  keywords: Keyword[];
};

type Trigger = {
  id: string;
  type: string;
};

type Listener = {
  id: string;
  listener: Listeners;
  prompt: string;
  commentReply?: string | null;
  dmCount: number;
  commentCount: number;
};

type Post = {
  id: string;
  postid: string;
  caption?: string;
  media: string;
  mediaType: MediaType;
};

type Dms = {
  id: string;
  senderId?: string;
  receiver?: string;
  message?: string;
};

type Keyword = {
  id: string;
  word: string;
};
