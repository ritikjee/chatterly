import { MediaType } from "@/enums/automation";

export enum IntegrationType {
  INSTAGRAM = "INSTAGRAM",
}

export type INTEGRATIONS = {
  id: string;
  name: IntegrationType;
  createdAt: string;
  userId: string;
  token: string;
  expiresAt?: string;
  instagramId?: string;
};

export type INSTAGRAM_POSTS = {
  id: string;
  caption: string;
  media_url: string;
  media_type: MediaType;
  timestamp: string;
};
