import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";
import ApplicationCard from "../components/ApplicationCard";

const AdminPanel = () => {
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchApplications = async () => {
      try {
        const response = await fetch("http://localhost:3000/api/applications");
        if (!response.ok) {
          throw new Error("Failed to fetch applications");
        }
        const data = await response.json();
        setApplications(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchApplications();
  }, []);

  if (loading)
    return (
      <div className="d-flex justify-content-center mt-5">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );

  if (error)
    return (
      <div
        className="alert alert-danger mx-auto mt-5"
        style={{ maxWidth: "600px" }}
      >
        Error: {error}
      </div>
    );

  return (
    <div className="container-fluid mt-4">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h1>Application Management</h1>
        <button
          onClick={() => navigate("/add-application")}
          className="btn btn-primary"
        >
          Add New Application
        </button>
        <button
          onClick={() => navigate("/add-breakage")}
          className="btn btn-primary"
        >
          Add Breakage
        </button>
      </div>

      <div className="card">
        <div className="card-header bg-success text-white">
          <h3 className="mb-0">Applications List</h3>
        </div>
        <div className="card-body">
          {applications.length === 0 ? (
            <p className="text-muted">
              No applications found.
              <button
                onClick={() => navigate("/add-application")}
                className="btn btn-link p-0 ms-1"
              >
                Add one now.
              </button>
            </p>
          ) : (
            <div className="applications-list">
              {applications.map((app) => (
                <ApplicationCard key={app.id} app={app} />
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AdminPanel;
