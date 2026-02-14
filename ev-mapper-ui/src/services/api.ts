const API_BASE = "http://localhost:8080/api";

export async function createStation(lat: number, lng: number) {
  const response = await fetch(`${API_BASE}/stations`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      name: "New EV Station",
      address: "Dropped from map",
      lat,
      lng,
    }),
  });

  if (!response.ok) {
    throw new Error("Failed to create station");
  }

  return response.json();
}
