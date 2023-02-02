/* eslint-disable */

declare namespace NodeJS {
  interface ProcessEnv {
    NODE_ENV: string;
    VUE_ROUTER_MODE: 'hash' | 'history' | 'abstract' | undefined;
    VUE_ROUTER_BASE: string | undefined;
    SERVER_URL: string;
    SERVER_AUTH: string;
    DEMO_DISCORD_ACCOUNT: string;
    DEMO_DISCORD_PRIORITY: string;
    DEMO_WHATSAPP_ACCOUNT: string;
    DEMO_WHATSAPP_PRIORITY: string;
    DEMO_TELEGRAM_ACCOUNT: string;
    DEMO_TELEGRAM_PRIORITY: string;
  }
}
