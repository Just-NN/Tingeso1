import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import repairService from "../services/repair.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import './formcontrol.css';


const AddEditRepair = () => {
  const [idTicket, setIdTicket] = useState("");
  const [licensePlate, setLicensePlate] = useState("");
  const [repairType, setRepairType] = useState("");
  const [entryDate, setEntryDate] = useState("");
  const [exitDate, setExitDate] = useState("");
  const [exitTime, setExitTime] = useState("");
    const [pickupDate, setPickupDate] = useState("");
    const [pickupTime, setPickupTime] = useState("");
    const [totalRepairAmount, setTotalRepairAmount] = useState("");
    const [kmSurcharge, setKmSurcharge] = useState("");
    const [ageSurcharge, setAgeSurcharge] = useState("");
    const [delaySurcharge, setDelaySurcharge] = useState("");
    const [dayDiscount, setDayDiscount] = useState("");
    const [repairsDiscount, setRepairsDiscount] = useState("");
    const [basePrice, setBasePrice] = useState("");
    const [totalPrice, setTotalPrice] = useState("");
    const [titleRepairForm, setTitleRepair] = useState("Edit Repair");
  const { idRepair } = useParams();
  const navigate = useNavigate();

  const saveRepair = (r) => {
    r.preventDefault();

    const repair = { idRepair, idTicket, licensePlate, repairType, entryDate, exitDate, exitTime, pickupDate, pickupTime, totalRepairAmount, kmSurcharge, ageSurcharge, delaySurcharge, dayDiscount, repairsDiscount, basePrice, totalPrice };
    if (idRepair) {
      //Actualizar Datos Empelado
      repairService
        .updateRepair(repair)
        .then((response) => {
          console.log("Repair updated", response.data);
          navigate("/repairs/list");
        })
        .catch((error) => {
          console.log(
            "Error while updating repair",
            error
          );
        });
    } else {
      //Crear nuevo empleado
      repairService
        .saveRepair(repair)
        .then((response) => {
          console.log("The repair has been created", response.data);
          navigate("/repairs/list");
        })
        .catch((error) => {
          console.log(
            "Error while creating",
            error
          );
        });
    }
  };

  useEffect(() => {
    if (idRepair) {
      setTitleRepair(idRepair);
      repairService
        .get(idRepair)
        .then((repair) => {
          setIdTicket(repair.data.idTicket);
            setLicensePlate(repair.data.licensePlate);
            setRepairType(repair.data.repairType);
            setEntryDate(repair.data.entryDate);
            setExitDate(repair.data.exitDate);
            setExitTime(repair.data.exitTime);
            setPickupDate(repair.data.pickupDate);
            setPickupTime(repair.data.pickupTime);
            setTotalRepairAmount(repair.data.totalRepairAmount);
            setKmSurcharge(repair.data.kmSurcharge);
            setAgeSurcharge(repair.data.ageSurcharge);
            setDelaySurcharge(repair.data.delaySurcharge);
            setDayDiscount(repair.data.dayDiscount);
            setRepairsDiscount(repair.data.repairsDiscount);
            setBasePrice(repair.data.basePrice);
            setTotalPrice(repair.data.totalPrice);

        })
        .catch((error) => {
          console.log("Error: ", error);
        });
    } else {
      setTitleRepair("New repair");
    }
  }, [idRepair]);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h3> {titleRepairForm} </h3>
      <hr />
      <form>
        <FormControl fullWidth>
          <TextField
            id="idTicket"
            label="idTicket"
            type={"number"}
            value={idTicket}
            variant="standard"
            onChange={(r) => setIdTicket(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="licensePlate"
            label="License Plate"
            type="number"
            value={licensePlate}
            variant="standard"
            onChange={(r) => setLicensePlate(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="repairType"
            label="Repair Type"
            value={repairType}
            type={"number"}
            variant="standard"
            onChange={(r) => setEntryDate(r.target.value)}

          />
            <MenuItem value={1}>Frenos</MenuItem>
            <MenuItem value={2}>Sisterma de Refrigeración</MenuItem>
            <MenuItem value={3}>Motor</MenuItem>
            <MenuItem value={4}>Transmisión</MenuItem>
            <MenuItem value={5}>Sistema Eléctrico</MenuItem>
            <MenuItem value={6}>Sistema de Escape</MenuItem>
            <MenuItem value={7}>Neumáticos y Ruedas</MenuItem>
            <MenuItem value={8}>Suspensión y Dirección</MenuItem>
            <MenuItem value={9}>Aire Acondicionado y Calefacción</MenuItem>
            <MenuItem value={10}>Sistema de Combustible</MenuItem>
            <MenuItem value={11}>Parabrisas y Cristales</MenuItem>

        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="entryDate"
            label="Entry Date"
            inputFormat="yyyy-MM-dd'T'HH:mm:ss.SSSxxx"
            value={entryDate}
            variant="standard"
            onChange={(r) => setEntryDate(r.target.value)}
            renderInput={(params) => <TextField {...params} />}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="exitDate"
            label="Exit Date"
            value={exitDate}
            inputFormat="yyyy-MM-dd'T'HH:mm:ss.SSSxxx"
            variant="standard"
            defaultValue="A"
            onChange={(r) => setExitDate(r.target.value)}
            renderInput={(params) => <TextField {...params} />}
            style={{ width: "25%" }}
          >
          </TextField>
        </FormControl>
        <FormControl fullWidth>
          <TextField
              id="pickupDate"
              label="Pickup Date"
              value={pickupDate}
              inputFormat="yyyy-MM-dd'T'HH:mm:ss.SSSxxx"
              variant="standard"
              onChange={(r) => setPickupDate(r.target.value)}
              renderInput={(params) => <TextField {...params} />}
              style={{ width: "25%" }}
          >
          </TextField>
        </FormControl>

        <FormControl>
          <FormControl fullWidth>
            <TextField
              id="pickupTime"
              label="Pickup Time"
              type="time"
              value={pickupTime}
              onChange={(r) => setPickupTime(r.target.value)}
              style={{ width: "100%" }}
            />
          </FormControl>
          <FormControl fullWidth>
            <TextField
              id="exitTime"
              label="Exit Time"
              type="time"
              value={exitTime}
              onChange={(r) => setExitTime(r.target.value)}
              style={{ width: "100%" }}
            />
          </FormControl>

          <Button
            variant="contained"
            color="info"
            onClick={(r) => saveRepair(r)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/employee/list">Back to List</Link>
    </Box>
  );
};

export default AddEditRepair;
