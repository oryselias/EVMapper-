import MapView, { Marker } from "react-native-maps";
import { StyleSheet } from "react-native";

type Props = {
  stations: any[];
  onAdd: (lat: number, lng: number) => void;
};

export default function PlatformMap({ stations, onAdd }: Props) {
  return (
    <MapView
      style={styles.map}
      initialRegion={{
        latitude: 12.9716,
        longitude: 77.5946,
        latitudeDelta: 0.05,
        longitudeDelta: 0.05,
      }}
      onPress={(e) => {
        const { latitude, longitude } = e.nativeEvent.coordinate;
        onAdd(latitude, longitude);
      }}
    >
      {stations.map((s) => (
        <Marker
          key={s.id}
          coordinate={{ latitude: s.lat, longitude: s.lng }}
          title={s.name}
        />
      ))}
    </MapView>
  );
}

const styles = StyleSheet.create({
  map: { flex: 1 },
});
