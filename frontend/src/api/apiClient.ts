const API_VERSION = "v1";
export const BASE_URL = `api/${API_VERSION}`;

// A helper to make fetching cleaner
export const apiFetch = (
  endpoint: string,
  options?: RequestInit,
): Promise<Response> => {
  return fetch(`${BASE_URL}${endpoint}`, options);
};
