import { useState } from "react";
import {
  View,
  TouchableOpacity,
  Text,
  StyleSheet,
  TextInput,
  Button,
} from "react-native";
import MapViewWrapper from "../components/MapView.web";

type Step = "idle" | "picking" | "form";

export default function MapScreen() {
  const [step, setStep] = useState<Step>("idle");
  const [selected, setSelected] = useState<any>(null);
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");

  async function addStation() {
    await fetch("http://localhost:8080/api/stations", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        name,
        address,
        lat: selected.lat,
        lng: selected.lng,
      }),
    });

    setStep("idle");
    setSelected(null);
    setName("");
    setAddress("");
  }

  return (
    <View style={{ flex: 1 }}>
      {/* MAP */}
      <View style={{ flex: 1 }}>
        <MapViewWrapper
          picking={step === "picking"}
          onSelect={setSelected}
        />
      </View>

      {/* + BUTTON */}
      {step === "idle" && (
        <TouchableOpacity style={styles.fab} onPress={() => setStep("picking")}>
          <Text style={styles.fabText}>＋</Text>
        </TouchableOpacity>
      )}

      {/* ✔ BUTTON */}
      {step === "picking" && selected && (
        <TouchableOpacity style={styles.tick} onPress={() => setStep("form")}>
          <Text style={styles.tickText}>✔</Text>
        </TouchableOpacity>
      )}

      {/* FORM */}
      {step === "form" && (
        <View style={styles.form}>
          <Text style={styles.title}>Add Station</Text>

          <TextInput
            placeholder="Station Name"
            style={styles.input}
            value={name}
            onChangeText={setName}
          />

          <TextInput
            placeholder="Address"
            style={styles.input}
            value={address}
            onChangeText={setAddress}
          />

          <Button title="Add Station" onPress={addStation} />
        </View>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  fab: {
    position: "absolute",
    bottom: 30,
    right: 20,
    width: 60,
    height: 60,
    borderRadius: 30,
    backgroundColor: "#2e7d32",
    alignItems: "center",
    justifyContent: "center",
    zIndex: 1000,
  },
  fabText: {
    color: "white",
    fontSize: 32,
  },
  tick: {
    position: "absolute",
    top: 40,
    right: 20,
    width: 50,
    height: 50,
    borderRadius: 25,
    backgroundColor: "#1565c0",
    alignItems: "center",
    justifyContent: "center",
    zIndex: 1000,
  },
  tickText: {
    color: "white",
    fontSize: 22,
  },
  form: {
    position: "absolute",
    bottom: 0,
    width: "100%",
    backgroundColor: "white",
    padding: 16,
    borderTopLeftRadius: 16,
    borderTopRightRadius: 16,
    elevation: 10,
  },
  title: {
    fontSize: 18,
    marginBottom: 10,
  },
  input: {
    borderWidth: 1,
    borderColor: "#ccc",
    padding: 8,
    marginBottom: 10,
    borderRadius: 6,
  },
});
