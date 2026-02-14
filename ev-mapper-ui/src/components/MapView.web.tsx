import "leaflet/dist/leaflet.css";

import { MapContainer, TileLayer, Marker, useMapEvents, useMap } from "react-leaflet";
import { useState } from "react";
import { useEffect } from "react";
import L from "leaflet";

delete (L.Icon.Default.prototype as any)._getIconUrl;

L.Icon.Default.mergeOptions({
  iconRetinaUrl:
    "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png",
  iconUrl:
    "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png",
  shadowUrl:
    "https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png",
});

export default function MapViewWrapper({
    picking,
    onSelect,
}: {
    picking: boolean;
    onSelect: (pos: { lat: number; lng: number }) => void;
}) {
    const [tempPos, setTempPos] = useState<any>(null);

    function ClickHandler() {
        useMapEvents({
            click(e) {
                if (picking) {
                    setTempPos(e.latlng);
                    onSelect(e.latlng);
                }
            },
        });
        return null;
    }

    return (
        <MapContainer
            center={[12.9716, 77.5946]}
            zoom={13}
            style={{
                height: "100vh",
                width: "100vw",
                position: "absolute",
                top: 0,
                left: 0,
            }}
        >
            <ResizeFix />
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
            <ClickHandler />

            {tempPos && <Marker position={tempPos} />}
        </MapContainer>
    );
}
function ResizeFix() {
  const map = useMap();

  useEffect(() => {
    setTimeout(() => {
      map.invalidateSize();
    }, 100);
  }, []);

  return null;
}
