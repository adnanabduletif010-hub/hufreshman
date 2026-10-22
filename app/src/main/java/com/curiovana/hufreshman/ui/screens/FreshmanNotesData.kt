package com.curiovana.hufreshman.ui.screens

data class ShortNote(
    val id: String,
    val subjectId: String,
    val subject: String,
    val unit: String,
    val title: String,
    val summary: String,
    val colorHex: Long,
    val initialLikes: Int = 20
)

// Broad, comprehensive, high-yield curriculum study notes for all 11 Freshman Subjects (Units 1 - 7)
val sampleNotes: List<ShortNote> = listOf(
    // =========================================================================
    // 1. GENERAL PHYSICS (c1, Units 1 - 7)
    // =========================================================================
    ShortNote(
        id = "phys-u1",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 1",
        title = "Preliminaries: Measurement, Uncertainty & Vectors",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Physics is inherently a science of measurement dealing with matter, energy, and natural phenomena. This unit establishes the foundational language of physical inquiry: base and derived SI units, dimensional analysis, uncertainty estimation, significant figures, and the composition, resolution, and algebra of vectors.

🔍 DETAILED BREAKDOWN
1. Physical Quantities & The SI System:
   • Basic Quantities: Cannot be defined in terms of other quantities. The 7 SI Base Units (1960):
     - Length: meter (m) [L]
     - Mass: kilogram (kg) [M]
     - Time: second (s) [T]
     - Temperature: kelvin (K) [θ]
     - Electric Current: ampere (A) [I]
     - Amount of Substance: mole (mol) [1 or N]
     - Luminous Intensity: candela (cd) [J]
   • Derived Quantities & Dimensions:
     - Force (F): newton (N) = kg·m·s⁻² = kg·m/s² [MLT⁻²]
     - Speed / Velocity (v): m/s = m·s⁻¹ [LT⁻¹]
     - Pressure (P): pascal (Pa) = N/m² = kg·m⁻¹·s⁻² [ML⁻¹T⁻²]
     - Work / Energy (E): joule (J) = N·m = kg·m²·s⁻² [ML²T⁻²]
     - Power (P): watt (W) = J/s = kg·m²·s⁻³ [ML²T⁻³]
   • Key Conversion Factors:
     - Length: 1 in = 0.0254 m; 1 ft = 0.3048 m; 1 mi = 1609.34 m
     - Mass: 1 lb = 0.4536 kg; 1 metric ton (t) = 1000 kg; 1 oz = 0.02835 kg
     - Volume: 1 L = 0.001 m³ = 10⁻³ m³; 1 gallon = 0.00379 m³
     - Temperature: T(K) = [T(°F) - 32] × (5/9) + 273.15; T(K) = T(°C) + 273.15

2. Measurement Uncertainty & Errors:
   • Systematic Errors: Caused by miscalibrated instruments or flawed setups; consistently bias readings in one direction. Eliminated by pre-calibration against standard reference.
   • Random Errors: Unpredictable fluctuations around true mean due to experimental environment and scale reading limits.
   • Estimating Reading Uncertainty (σ_x):
     - Analog (Scale) Instruments: Half of smallest scale division: σ_x = (Smallest Increment) / 2 (e.g., standard ruler with 1 mm markings has σ_x = 0.5 mm = 0.05 cm).
     - Digital Instruments: Equal to the smallest display division: σ_x = Smallest Increment.
   • Measurement Reporting Format: x = x_best ± σ_x (only one uncertain digit reported).

3. Significant Figures (Digits) Rules:
   • Non-zero digits: Always significant (e.g., 24.7 has 3).
   • Zeros between non-zeros: Always significant (e.g., 1.0005 has 5).
   • Leading zeros: Never significant; merely locate decimal point (e.g., 0.0005 has 1, 0.0062 cm has 2).
   • Trailing zeros: Significant ONLY if a decimal point is present (e.g., 5.00 has 3, 4.0500 cm has 5; 300 is ambiguous, write 3.00 × 10² for 3 sig figs).
   • Calculation Rules:
     - Multiplication / Division: Final result retains same number of significant figures as factor with the FEWEST significant figures.
     - Addition / Subtraction: Final result rounded to the FEWEST decimal places of any term in calculation.

4. Vectors: Composition & Resolution:
   • Scalar vs Vector: Scalars have magnitude and unit only (mass, time, volume, speed). Vectors require magnitude, unit, and spatial direction (displacement, velocity, force, momentum).
   • Resultant Vector (R): Combined net vector of two or more vectors.
     - Head-to-Tail (Polygon) Method: Chain successive vectors tail-to-head; R connects first tail to final head.
     - Parallelogram Law: R = √(A² + B² - 2AB cos θ); Direction via Law of Sines: (sin θ)/R = (sin α)/A = (sin β)/B.
   • Vector Resolution (2D Components):
     - Horizontal: A_x = A cos θ; Vertical: A_y = A sin θ.
     - Magnitude: A = √(A_x² + A_y²); Angle: θ = tan⁻¹(A_y / A_x).
   • 3D Space & Direction Cosines:
     - Vector: A = A_x î + A_y ĵ + A_z k̂; Magnitude: A = √(A_x² + A_y² + A_z²).
     - Direction Cosines: cos θ_x = A_x / A, cos θ_y = A_y / A, cos θ_z = A_z / A, where cos² θ_x + cos² θ_y + cos² θ_z = 1.

5. Unit Vectors & Analytical Addition:
   • Unit Vector: Dimensionless vector of length exactly 1 indicating direction: u_A = A / |A|.
   • Cartesian Unit Vectors: î (+x axis), ĵ (+y axis), k̂ (+z axis).
   • For position vector r = x î + y ĵ + z k̂, unit vector:
     u_r = (x/r) î + (y/r) ĵ + (z/r) k̂, where r = √(x² + y² + z²).
   • Analytical Addition: Sum components independently:
     A + B = (A_x + B_x) î + (A_y + B_y) ĵ + (A_z + B_z) k̂.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Calculation Precision Distinction: Multiplication and division depend on the lowest count of SIGNIFICANT FIGURES, whereas addition and subtraction depend on the lowest number of DECIMAL PLACES.
• Reading Uncertainty Rule of Thumb: Analog instruments have uncertainty equal to HALF the smallest scale division (0.5 mm on a 1 mm ruler). Digital devices have uncertainty equal to the FULL smallest display step.
• Component Reference Angle Trap: A_x = A cos θ and A_y = A sin θ strictly assume θ is measured from the positive x-axis. If θ is given with the y-axis, A_x = A sin θ and A_y = A cos θ!
• Unit vectors are strictly dimensionless and always have magnitude exactly equal to 1.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 78
    ),
    ShortNote(
        id = "phys-u2",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 2",
        title = "Kinematics & Dynamics of Particles, Energy & Momentum",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
This unit establishes the foundations of classical particle mechanics: kinematics (describing 1D and 2D motion without regard to causes), particle dynamics (Newton's laws, friction, and gravitation), planetary motion (Kepler's laws), work-energy conservation, impulse-momentum, and collisions.

🔍 DETAILED BREAKDOWN
1. Kinematics in 1D & 2D Motion:
   • Basic Quantities:
     - Displacement: Δx = x_f - x_i (1D) or Δr = r_f - r_i (2D, vector). Distance (S) is scalar actual path length.
     - Velocity: Average v_av = Δr / Δt; Instantaneous v = dr/dt = lim_{Δt→0} (Δr / Δt).
     - Acceleration: Average a_av = Δv / Δt; Instantaneous a = dv/dt = lim_{Δt→0} (Δv / Δt).
   • Constant Acceleration Equations (1D & 2D):
     - v_f = v_i + a t
     - v_av = ½(v_i + v_f)
     - Δr = v_i t + ½ a t²
     - v_f² = v_i² + 2 a Δr
   • Free Fall Motion (Choosing upward as positive, a = -g, g ≈ 9.8 m/s²):
     - v = u - gt;  y - y_i = ut - ½gt²;  v² = u² - 2g(y - y_i).
     - Gravity varies slightly with latitude (greater at poles) and altitude (greater at sea level).
   • Projectile Motion (Parabolic Trajectory):
     - Horizontal: a_x = 0, v_x = u cos θ (constant), x(t) = (u cos θ) t.
     - Vertical: a_y = -g, v_y = u sin θ - gt, y(t) = (u sin θ) t - ½gt².
     - Time to Peak: t = (u sin θ) / g.
     - Total Time of Flight: t_tot = (2u sin θ) / g.
     - Maximum Height: H = (u² sin² θ) / (2g).
     - Horizontal Range: R = (u² sin 2θ) / g (Maximum at θ = 45°: R_max = u² / g; complementary angles yield equal range).

2. Newton's Laws of Motion & Forces:
   • Four Fundamental Interactions: Gravitational, Electromagnetic, Strong nuclear, Weak nuclear.
   • Forces Classification:
     - Contact: Normal force (perpendicular), Tension (taut cables), Spring force (Hooke's Law: F_s = -kx), Friction.
     - Field (Non-Contact): Gravitational, Electrostatic, Magnetic.
   • Newton's Three Laws:
     - 1st Law (Inertia): ΣF = 0 ⇒ a = 0 (velocity is constant in inertial frames).
     - 2nd Law: ΣF = m a (acceleration is proportional to net force and along net force).
     - 3rd Law: F_AB = -F_BA (equal and opposite action-reaction pairs acting on DIFFERENT bodies; never cancel).
   • Friction Forces:
     - Static Friction: f_s ≤ f_s,max = μ_s N (opposes impending motion).
     - Kinetic Friction: f_k = μ_k N (opposes active sliding, μ_k < μ_s). Both coefficients independent of contact surface area.

3. Circular Motion, Gravitation & Kepler's Laws:
   • Uniform Circular Motion: Constant speed, continuously changing velocity direction.
     - Centripetal Acceleration: a_c = v² / r (directed radially inward).
     - Period: T = 2πr / v.
   • Universal Gravitation: F = G m M / r² (G = 6.674 × 10⁻¹¹ N·m²/kg²).
     - Surface Gravity: g = G M / r² ≈ 9.8 m/s².
   • Kepler's Laws of Planetary Motion:
     - 1st Law (Orbits): Planets move in elliptical orbits with the Sun at one focus.
     - 2nd Law (Areas): Radius vector sweeps out equal areas in equal times (fastest at perihelion, slowest at aphelion).
     - 3rd Law (Harmony): T² / R³ = constant for all bodies orbiting the same central mass.
   • Weightlessness: Experienced when there are no supporting normal contact forces (objects and astronauts in orbit are in continuous free fall under gravity).

4. Work, Energy & Power:
   • Work: W = F · Δr = |F||Δr| cos θ (Joules).
     - Spring Work: W_s = ½ k x_i² - ½ k x_f² (by spring); W_app = ½ k x_f² - ½ k x_i² (by applied agent).
   • Energies:
     - Kinetic Energy: KE = ½ m v².
     - Gravitational Potential Energy: ΔU_g = mg Δy.
   • Work-Energy Theorem: W_net = ΔKE = ½ m v_f² - ½ m v_i².
   • Conservation of Mechanical Energy:
     - Isolated Conservative: K_i + U_i = K_f + U_f = constant.
     - With Non-Conservative Forces (Friction): ΔK + ΔU = -f_k d.
   • Power: Rate of doing work. Average: P_av = W / Δt; Instantaneous: P = F · v.
     - Units: Watt (W = J/s); 1 hp = 746 W; 1 kWh = 3.6 × 10⁶ J.

5. Momentum, Impulse & Collisions:
   • Linear Momentum: p = m v (kg·m/s). Net force: F_net = Δp / Δt.
   • Impulse-Momentum Theorem: I = F_net Δt = Δp = p_f - p_i.
   • Conservation of Linear Momentum: p_1i + p_2i = p_1f + p_2f (strictly holds for all isolated systems).
   • Types of Collisions:
     - Elastic: Momentum AND Kinetic Energy are BOTH conserved. 1D relative velocity: v_1i - v_2i = -(v_1f - v_2f).
     - Inelastic: Momentum conserved, but Kinetic Energy is NOT conserved (some converts to heat, sound, deformation).
     - Perfectly Inelastic: Bodies stick together with common velocity: v_f = (m₁ v_1i + m₂ v_2i) / (m₁ + m₂).
   • Center of Mass: Point where total system mass M = Σ m_i behaves as concentrated:
     r_CM = (Σ m_i r_i) / M, with components x_CM = (Σ m_i x_i) / M, etc.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Action-Reaction Pairs Never Cancel: Newton's 3rd law forces always act on TWO DIFFERENT bodies. Never include an action-reaction pair on the same free-body diagram!
• Horizontal Projectile Velocity is Invariant: Because horizontal acceleration a_x = 0, horizontal velocity v_x = u cos θ remains unchanged across the entire flight.
• Range Symmetry: Complementary launch angles (e.g. θ = 30° and θ = 60°) achieve the EXACT SAME horizontal range for the same launch speed.
• Collision Distinctions: In ALL isolated collisions, linear momentum is conserved. ONLY in elastic collisions is kinetic energy conserved.
• Friction Rule: Static friction is an inequality (f_s ≤ μ_s N) that adjusts to match applied force until impending motion threshold μ_s N is reached.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 85
    ),
    ShortNote(
        id = "phys-u3",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 3",
        title = "Fluid Mechanics & Bulk Properties of Matter",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
This unit explores the mechanics of fluids and deformable bulk matter. It covers elasticity and plasticity, stress, strain, elastic moduli, hydrostatics (density, hydrostatic pressure, Pascal's and Archimedes' principles), and hydrodynamics (equation of continuity and Bernoulli's equation).

🔍 DETAILED BREAKDOWN
1. Elasticity & Mechanical Properties of Bulk Matter:
   • Elastic vs Plastic Behavior:
     - Elastic: Material returns completely to original shape and size when deforming forces are removed.
     - Elastic Limit: Maximum stress beyond which deformation becomes permanent and irreversible (plastic deformation).
   • Stress & Strain Definitions:
     - Stress: Deforming force per unit area: Stress = F / A (SI Unit: Pascal, Pa = N/m²).
     - Strain: Dimensionless fractional deformation: Strain = (Change in configuration) / (Initial configuration).
   • Three Modes of Deformation:
     - Tensile / Compressive: Stress = F_⊥ / A; Strain = Δl / l₀.
     - Shear: Stress = F_∥ / A; Strain = x / h = tan φ (lateral displacement x, height h, shear angle φ).
     - Volume (Bulk): Stress = ΔP = ΔF_⊥ / A; Strain = ΔV / V₀.
   • Elastic Moduli (Stress / Strain within proportional limit):
     - Young's Modulus (Y): Longitudinal resistance: Y = (F_⊥ l₀) / (A Δl).
     - Shear Modulus (S): Resistance to internal planar sliding: S = (F_∥ / A) / (x / h). (Fluids cannot sustain shear/tension; S = Y = 0 for liquids).
     - Bulk Modulus (B): Volume compression resistance: B = -ΔP / (ΔV / V₀) (negative sign ensures B > 0). Compressibility k = 1 / B.
     - Strain Energy: Stored elastic potential energy: U = ½ k x².

2. Density & Hydrostatic Pressure:
   • Density (ρ): ρ = m / V (kg/m³). Specific Gravity (SG) = ρ_substance / ρ_water (where ρ_water = 1000 kg/m³ at 4°C).
   • Hydrostatic Pressure with Depth:
     - P = P₀ + ρ g h (where P₀ is surface pressure, h is depth).
     - Pressure is isotropic (acts equally in all directions) and depends solely on depth h, independent of container geometry.
     - All points at identical horizontal level in a continuous static fluid have equal pressure.
   • Pressure Designations:
     - Atmospheric Pressure: P_atm ≈ 101.3 kPa = 1.013 × 10⁵ Pa (1 atm).
     - Gauge Pressure: P_gauge = P_system - P_atm = ρ g h.
     - Absolute Pressure: P_abs = P_gauge + P_atm.

3. Pascal’s & Archimedes' Principles:
   • Pascal's Principle: Pressure applied to an enclosed, incompressible fluid transmits undiminished throughout the fluid and container walls.
     - Hydraulic Lift: P₁ = P₂ ⇒ F₁ / A₁ = F₂ / A₂ ⇒ F₂ = F₁(A₂ / A₁).
   • Archimedes' Principle: An object fully or partially submerged experiences an upward buoyant force equal to the weight of displaced fluid:
     - Buoyant Force: F_b = W_fluid = m_fluid g = ρ_fluid V_displaced g.
     - Origin: Increase of hydrostatic pressure with depth creates an upward net vertical pressure force.
     - Apparent Weight: W_apparent = W_real - F_b.
     - Floating Condition: Object floats in equilibrium when F_b = W_object ⇒ ρ_object / ρ_fluid = V_submerged / V_total.

4. Fluid Dynamics & Moving Fluids:
   • Flow Regimes:
     - Laminar Flow: Smooth, steady streamlines where adjacent layers slide without mixing.
     - Turbulent Flow: Chaotic, eddies and vortices above critical velocity, high energy dissipation.
   • Ideal Fluid Assumptions: Non-viscous (no internal friction), Steady, Incompressible (constant density), Irrotational.
   • Equation of Continuity (Conservation of Mass):
     - Volume Flow Rate (Flux): Q = Av = ΔV / Δt = constant (m³/s).
     - A₁ v₁ = A₂ v₂ (velocity is inversely proportional to cross-sectional area).
   • Bernoulli's Equation (Conservation of Energy per unit volume):
     - P + ½ ρ v² + ρ g y = constant along a streamline.
     - P₁ + ½ ρ v₁² + ρ g y₁ = P₂ + ½ ρ v₂² + ρ g y₂.
     - Terms: P is static pressure; ½ ρ v² is dynamic pressure (kinetic energy density); ρ g y is potential energy density.
     - Bernoulli / Venturi Effect: For horizontal flow (y₁ = y₂), higher fluid speed causes lower static pressure (explains airplane wing lift, atomizers, and chimney drafts).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Fluid Moduli Constraint: Liquids possess Bulk Modulus (B), but CANNOT have Young's or Shear Moduli because they cannot sustain tensile or shearing stresses.
• Buoyant Force Independence: F_b depends STRICTLY on the density of the surrounding FLUID and the DISPLACED volume (F_b = ρ_fluid V_disp g). It does NOT depend on the object's mass or composition!
• Gauge vs Absolute Pressure: Tire pressure gauges and manometers measure GAUGE pressure (P_gauge = ρ g h). Always add atmospheric pressure (101.3 kPa) when absolute pressure is required.
• Continuity & Bernoulli Tandem: Narrowing a pipe (A decreases) forces fluid velocity to increase (v increases via continuity), which directly causes static fluid pressure to DROP (Bernoulli effect).
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 92
    ),
    ShortNote(
        id = "phys-u4",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 4",
        title = "Heat, Thermodynamics & Thermal Expansion",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Thermodynamics governs the transformation of thermal energy into mechanical work and the macroscopic properties of matter. This unit covers the Zeroth Law, temperature scales, thermal expansion (linear, areal, volumetric), calorimetry and latent heats, heat transfer mechanisms (conduction, convection, radiation), and the First Law of Thermodynamics across fundamental gas processes.

🔍 DETAILED BREAKDOWN
1. Temperature, Equilibrium & Zeroth Law:
   • Basic Definitions:
     - Heat: Transient energy in transit between a system and surroundings caused solely by a temperature gradient. (Matter does not "contain" heat; it possesses Internal Energy).
     - Thermal Equilibrium: Condition where two bodies in thermal contact cease exchanging net energy and share the same temperature.
   • Zeroth Law of Thermodynamics (Fowler): If body A is in thermal equilibrium with B, and B is in thermal equilibrium with C, then A is in thermal equilibrium with C. (Establishes temperature as a universal measurable state variable).
   • Temperature Scales & Conversions (Fixed at water freezing and boiling points at 1 atm):
     - Celsius (°C): 0°C to 100°C.
     - Fahrenheit (°F): 32°F to 212°F. Conversion: T_F = (9/5) T_C + 32; T_C = (5/9)(T_F - 32).
     - Kelvin (K): Absolute scale based on triple point of water (273.16 K); 0 K is absolute zero (-273.15°C). Conversion: T_K = T_C + 273.15.

2. Thermal Expansion of Matter:
   • Linear Expansion (Solids): Δl = α l₀ ΔT ⇒ l = l₀(1 + α ΔT), where α is coefficient of linear expansion (K⁻¹).
     - Bimetallic Strip: Two bonded metals with unequal expansion coefficients (α_A ≠ α_B). Bends toward smaller-α metal when heated, and opposite when cooled.
   • Areal Expansion: ΔA = β A₀ ΔT ⇒ A = A₀(1 + β ΔT), where areal coefficient β = 2α.
   • Volume Expansion (Solids & Liquids): ΔV = γ V₀ ΔT ⇒ V = V₀(1 + γ ΔT), where volume coefficient γ = 3α.

3. Heat Capacity, Calorimetry & Phase Transitions:
   • Specific Heat Capacity (c): Heat needed to raise 1 kg of substance by 1°C (or 1 K): Q = m c ΔT. (Molar: Q = n C ΔT).
   • Calorimetry Principle: In an insulated system, heat conservation requires:
     Q_lost (by hotter bodies) = Q_gained (by cooler bodies).
   • Latent Heat (L): Heat absorbed or released per unit mass during phase transition at CONSTANT temperature: Q_L = ± m L.
     - Latent Heat of Fusion (L_f): Solid ↔ Liquid (for water at 0°C: L_f ≈ 3.33 × 10⁵ J/kg).
     - Latent Heat of Vaporization (L_v): Liquid ↔ Gas (for water at 100°C: L_v ≈ 2.26 × 10⁶ J/kg).

4. Heat Transfer Mechanisms:
   • Conduction: Energy transfer through microscopic atomic collisions and free electrons in direct physical contact (dominant in solids; metals are prime conductors due to high free-electron density).
   • Convection: Heat transfer via bulk displacement and circulation of heated fluid matter (liquids and gases). Thermal buoyancy drives warm, less dense fluid upward while cooler, denser fluid descends.
   • Radiation: Energy propagation via electromagnetic waves (primarily infrared). Operates through vacuum without material medium. Dark, matte surfaces are superior absorbers and emitters; obeys inverse-square distance law.
   • Direct Burning: Spreading of heat by continuous direct flame contact along available combustible fuel.

5. First Law of Thermodynamics & Gas Processes:
   • First Law Formulation: Conservation of energy for thermodynamic systems:
     ΔU = Q + W  (where W = -P ΔV is work done ON the system).
     - State vs Path Functions: Internal energy U is a state function (ΔU is path-independent). Heat Q and Work W are path-dependent.
     - Work on PV Diagram: Area under the curve during expansion or compression: W = -∫ P dV.
   • Special Ideal Gas Thermodynamic Processes:
     - Isolated System: Q = 0, W = 0 ⇒ ΔU = 0 (Internal energy is conserved).
     - Cyclic Process: System returns to initial state ⇒ ΔU = 0 ⇒ Q_net = -W_net (Net heat equals net work delivered; area inside PV loop).
     - Isochoric (Isovolumetric, ΔV = 0): No boundary work (W = 0) ⇒ ΔU = Q (all added heat directly raises temperature).
     - Adiabatic (Q = 0): Rapid process with no thermal exchange ⇒ ΔU = W (gas cools during expansion, warms during compression).
     - Isothermal (Constant T, ΔT = 0): For ideal gas, ΔU = 0 ⇒ Q = -W = nRT ln(V_f / V_i) (all added heat converts to expansion work).
     - Isobaric (Constant P): W = -P(V_f - V_i) ⇒ ΔU = Q - P(V_f - V_i).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Internal Energy vs Heat: A thermodynamic system possesses Internal Energy (U), NEVER heat! Heat (Q) exists purely as energy in transit across boundaries.
• Thermal Expansion Ratios: Memorize the dimensional proportionality: Linear α : Area β : Volume γ = 1 : 2 : 3.
• Constant Temperature During Phase Changes: During boiling or melting, adding heat does NOT change the temperature (ΔT = 0); all thermal energy Q = mL breaks intermolecular bonds.
• Isothermal Ideal Gas Identity: Because internal energy of an ideal gas depends solely on temperature U = U(T), in ANY isothermal process (ΔT = 0), ΔU is strictly 0!
• Work on PV Diagrams: Expansion (V_f > V_i) does work ON surroundings (work done on gas is negative); compression does positive work on the gas.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 81
    ),
    ShortNote(
        id = "phys-u5",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 5",
        title = "Oscillations, Waves & Geometric Optics",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
This unit explores periodic motion, wave propagation, acoustic phenomena, and geometric optics. It covers Simple Harmonic Motion (mass-spring and simple pendulum), wave characteristics (mechanical vs electromagnetic, transverse vs longitudinal), resonance, the Doppler effect, wave interference, and image formation by plane mirrors and thin lenses.

🔍 DETAILED BREAKDOWN
1. Simple Harmonic Motion (SHM):
   • Dynamics & Kinematics:
     - Restoring Force (Hooke's Law): F_s = -kx.
     - Equation of Motion: ma = -kx ⇒ a = -(k/m)x = -ω²x.
     - Angular Frequency: ω = √(k/m) = 2πf = 2π / T; Period T = 2π√(m/k); Frequency f = 1/T.
     - Kinematics (equilibrium start): x(t) = A sin(ωt); v(t) = ωA cos(ωt); a(t) = -ω²A sin(ωt).
     - Extrema: Maximum speed v_max = ωA (at equilibrium x = 0); Maximum acceleration a_max = ω²A (at turnaround x = ±A).
   • Conservation of Energy in SHM:
     - Potential Energy: PE = ½ k x² = ½ k A² sin²(ωt).
     - Kinetic Energy: KE = ½ m v² = ½ k A² cos²(ωt).
     - Total Mechanical Energy: E = KE + PE = ½ k A² = constant (E ∝ A²).
   • The Simple Pendulum (for small angles θ < 15°):
     - Restoring Force: F_t = -mg sin θ ≈ -(mg/L)s.
     - Period: T = 2π√(L/g).
     - Dependent solely on length L and gravitational field g; completely independent of the mass of the bob!

2. Wave Characteristics & Classification:
   • Basic Terms: Crest (peak), Trough (valley), Amplitude (A), Wavelength (λ).
   • Wave Speed Equation: v = λ f = λ / T (constant for a given homogeneous medium).
   • Classification of Waves:
     - By Medium: Mechanical waves (require physical medium: sound, seismic, water) vs. Electromagnetic waves (propagate through medium or vacuum at c ≈ 3.0 × 10⁸ m/s: light, X-rays, radio).
     - By Particle Vibration: Transverse (vibrations perpendicular to propagation: light, string waves, surface ripples) vs. Longitudinal (vibrations parallel to propagation via compressions and rarefactions: sound waves).
     - By Boundary: Travelling waves (unbounded) vs. Standing waves (bounded interference).

3. Resonance, Wave Interactions & The Doppler Effect:
   • Resonance: Phenomenon where driving frequency matches the natural frequency f₀ of a system, producing maximum energy transfer and dramatic amplitude peaks.
   • Wave Behaviors:
     - Reflection: Bouncing of waves at boundary interfaces (angle of incidence = angle of reflection).
     - Refraction: Directional bending when entering a medium with different wave speed.
     - Diffraction: Spreading and bending around obstacles or apertures (most pronounced when aperture ~ λ).
     - Interference & Beats: Superposition of waves; slightly different frequencies produce pulsating beats: f_beat = |f₁ - f₂|.
   • The Doppler Effect (Sound Waves):
     - Apparent frequency shift caused by relative motion between source and observer:
       f_o = f_s [(v ± v_o) / (v ∓ v_s)]
     - Sign Rule: Use upper signs (+v_o, -v_s) for APPROACH (frequency increases); use lower signs (-v_o, +v_s) for RECEDING (frequency decreases).

4. Geometric Optics: Mirrors & Thin Lenses:
   • Plane Mirror Images: Virtual, erect (upright), identical size (h_i = h_o, magnification m = 1), and image distance equals object distance (d_i = d_o).
   • Thin Lens Equation & Magnification:
     - Lens Formula: 1/s_o + 1/s_i = 1/f.
     - Magnification: m = h_i / h_o = -s_i / s_o.
   • Convex (Converging) Lens (f > 0):
     - Object beyond 2F: Real, inverted, diminished (between F and 2F).
     - Object at 2F: Real, inverted, same size (at 2F, m = -1).
     - Object between F and 2F: Real, inverted, magnified (beyond 2F).
     - Object at F: Refracted rays parallel; image at infinity.
     - Object between F and lens: Virtual, erect, magnified (magnifying glass, on same side as object).
   • Concave (Diverging) Lens (f < 0):
     - Formed images are ALWAYS virtual, erect, and diminished, regardless of object position!

💡 EXAM TIPS & HIGH-YIELD FOCUS
• SHM Energy-Amplitude Scaling: Total mechanical energy is proportional to the SQUARE of the amplitude (E ∝ A²). Doubling amplitude quadruples the stored energy!
• Pendulum Mass Invariance: Changing the mass of a simple pendulum's bob has ZERO effect on its period T = 2π√(L/g).
• Doppler Shift Direction: Approaching entities always hear/see higher frequencies (blueshift / higher pitch), while receding entities observe lower frequencies (redshift / lower pitch).
• Sound Waves Cannot Travel in a Vacuum: Sound is a longitudinal mechanical wave requiring a material medium. Light is an electromagnetic wave and travels freely in a vacuum.
• Concave Lens Image Rule: Any ray tracing through a concave (diverging) lens yields exclusively virtual, upright, and diminished images.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 89
    ),
    ShortNote(
        id = "phys-u6",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 6",
        title = "Electromagnetism, Induction & Solid-State Electronics",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
This unit covers the unified framework of electrostatics, direct-current circuits, magnetostatics, electromagnetic induction, and semiconductor electronics (energy band theory, p-n junction diodes, rectification, bipolar transistors, and basic logic gates).

🔍 DETAILED BREAKDOWN
1. Electrostatics: Coulomb's Law & Electric Fields:
   • Electric Charge: Inherent property of matter; Elementary charge unit e = 1.6 × 10⁻¹⁹ C. Like charges repel, unlike attract.
   • Coulomb’s Law: Electrostatic force between point charges q₁, q₂ separated by distance r:
     F = K (q₁ q₂ / r²) r̂, where Coulomb constant K = 1/(4πε₀) ≈ 9.0 × 10⁹ N·m²/C² (ε₀ ≈ 8.85 × 10⁻¹² C²/(N·m²)).
     - Follows Newton’s 3rd Law: F₁₂ = -F₂₁. Satisfies vector superposition: F_net = Σ F_i.
   • Electric Field (E): Force per unit positive test charge: E = F_e / q₀ = (1/4πε₀)(q / r²) r̂ (N/C or V/m).
     - Field Lines: Emerge radially from positive charges, terminate on negative charges or infinity. Density reflects field strength; lines never intersect.
   • Potential Energy & Voltage:
     - Potential Energy: U = (1/4πε₀)(q₁ q₂ / r).
     - Electric Potential (V): V = U / q (Volt, V = J/C). Potential difference: ΔU = q ΔV.

2. Electric Current, Circuits & Kirchhoff's Rules:
   • Current (I): Rate of charge flow: I = ΔQ / Δt (Ampere, A = C/s). 1 A = 6.25 × 10¹⁸ electrons/sec.
   • Ohm’s Law & Resistance:
     - Microscopic: J = σ E, with resistivity ρ = 1/σ = E / J (Ω·m).
     - Macroscopic: V = IR, where resistance R = ρ(l / A) (Ohms, Ω).
     - Electric Power: P = IV = I²R = V² / R (Watts, W).
   • Resistor Combinations:
     - Series: Current identical; R_eq = R₁ + R₂ + ... + R_N.
     - Parallel: Voltage identical; 1/R_eq = 1/R₁ + 1/R₂ + ... + 1/R_N.
   • Kirchhoff's Circuit Rules:
     - Junction Rule (Charge Conservation): Σ I_in = Σ I_out ⇒ Σ I = 0 at any node.
     - Loop Rule (Energy Conservation): Algebraic sum of potential differences around any closed circuit loop is zero: Σ V = 0.

3. Magnetism & Electromagnetic Induction:
   • Magnetic Force on Moving Charge:
     - F_B = q(v × B); Magnitude: F = |q| v B sin θ. Direction given by Right-Hand Rule (reversed for negative charges).
     - Force is zero if charge is at rest (v = 0) or moves parallel/antiparallel to field (θ = 0° or 180°).
     - Magnetic force does ZERO work on the charge (velocity vector is always perpendicular to force: F ⊥ v).
     - Units: Tesla (1 T = 1 N/(A·m) = 10⁴ Gauss).
   • Magnetic Flux (Φ): Total field penetrating area A: Φ = B · A = BA cos θ (Weber, Wb = T·m²).
   • Faraday’s Law of Induction: Induced emf is proportional to time rate of magnetic flux change:
     ℰ = -N (ΔΦ / Δt).
   • Lenz’s Law: The negative sign reflects that induced current always opposes the flux change producing it.

4. Semiconductor Physics & Energy Band Theory:
   • Band Structure: Valence Band (V.B.), Conduction Band (C.B.), and Forbidden Energy Gap (E_g).
     - Conductors: Overlapping bands (E_g ≈ 0); conductivity decreases as temperature rises.
     - Semiconductors: Small energy gap (E_g < 3 eV; 1.1 eV for Si, 0.7 eV for Ge); conductivity INCREASES with temperature.
     - Insulators: Large band gap (E_g > 3 eV; ~6 eV for diamond).
   • Extrinsic Semiconductors (Doping):
     - N-Type: Doped with pentavalent donors (P, As, Sb). Extra free conduction electrons; majority: electrons, minority: holes.
     - P-Type: Doped with trivalent acceptors (B, Al, In). Lattice vacancies (holes); majority: holes, minority: electrons.

5. Diodes, Transistors & Logic Gates:
   • P-N Junction Diode:
     - Depletion region and barrier potential: 0.7 V for Si, 0.3 V for Ge.
     - Biasing: Forward bias (P to +, N to -; conducts in mA, ON); Reverse bias (P to -, N to +; widens barrier, microampere leakage, OFF).
     - Rectification: Half-wave (1 diode) vs Full-wave bridge (4 diodes, continuous unidirectional DC output).
   • Bipolar Junction Transistor (BJT):
     - Terminals: Emitter (heavily doped), Base (very thin, lightly doped), Collector (moderately doped, collects carriers).
     - Active Mode Bias: Emitter-base forward-biased, collector-base reverse-biased.
     - Current Relation: I_E = I_B + I_C; Current Gain: β = I_C / I_B. Common Emitter (CE) amplifies with 180° phase inversion.
   • Fundamental Digital Logic Gates:
     - AND (Output 1 iff both inputs 1), OR (Output 1 if any input 1), NOT (Inverter), NAND (Universal gate), NOR (Universal gate).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Zero Magnetic Work: Magnetic force F = q(v × B) is strictly perpendicular to instantaneous velocity (F ⊥ v). Therefore, magnetic fields change ONLY the direction of motion, NEVER the speed or kinetic energy!
• Semiconductor Temperature Anomaly: Unlike metals whose resistance increases with temperature, semiconductors exhibit NEGATIVE temperature coefficient of resistance (heating frees more electron-hole pairs, decreasing resistance).
• Junction Barrier Potentials: Keep in mind the baseline barrier voltages: 0.7 V for Silicon and 0.3 V for Germanium.
• Transistor Current Conservation: Emitter current is always the sum of base and collector currents: I_E = I_B + I_C.
• Kirchhoff Conservation Principles: Junction rule is conservation of CHARGE; Loop rule is conservation of ENERGY.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 112
    ),
    ShortNote(
        id = "phys-u7",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 7",
        title = "Cross-Cutting Applications of Physics",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Physics serves as the foundational science connecting subatomic phenomena to macroscopic technologies. This unit explores modern cross-disciplinary applications: agrophysics and soil dynamics, heavy industrial machines (motors and generators), medical diagnostics and radiation physics, geophysics, telecommunications, and sustainable renewable energy systems.

🔍 DETAILED BREAKDOWN
1. Overview of Physics in Science & Technology:
   • Historical Foundations & Technological Impacts:
     - Newtonian Mechanics: Industrial revolution, machinery design, automation, robotics, and kinetic simulations.
     - Thermodynamics: Heat engines, steam locomotives, refrigeration, and power generation cycles.
     - Electricity & Magnetism: Power grids, transformers, electric motors, generators, and electronics.
     - Quantum Mechanics: Lasers, semiconductor bandgap engineering, atomic clocks, and quantum computing.
     - Nuclear Physics: Nuclear power generation, medical radioisotopes, radiotherapy, and Nuclear Magnetic Resonance (NMR).
     - Condensed Matter Physics: Solid-state microelectronics, integrated circuits, liquid crystals (LCDs), and superconductors.
     - Optics & Photonics: Fiber-optic communication cables, optical microscopes, telescopes, imaging sensors, and lasers.

2. Physics in Agriculture & Environment (Agrophysics):
   • Purpose: Applies physical laws to agro-ecosystems to prevent soil degradation, optimize water usage, reduce greenhouse emissions, and enhance crop yields.
   • Soil as a Three-Phase System (Optimal upland plant conditions by volume):
     - Solid Phase: ~50% (mineral particles and organic matter).
     - Liquid Phase (Soil Water): ~25% (dissolved nutrients in soil solution).
     - Gaseous Phase (Soil Air): ~25% (O₂ and CO₂ exchange for root respiration).
   • Physical Properties of Soil:
     - Soil Density: Particle density (solid phase only) vs. Bulk density (dry soil mass divided by total volume including pores). Expressed in g/cm³ or kg/m³.
     - Soil Porosity: Fraction or percentage of void spaces relative to total volume; includes void ratio, air-filled porosity, and saturation degree.
     - Soil Moisture Content & Retention: Gravimetric/volumetric water content and matric potential curves dictated by pore-size distribution.
   • Surface Energy Balance at Soil–Atmosphere Interface:
     Assuming negligible heat storage in a thin surface layer:
     R_n + G + LE + H = 0
     - R_n: Net incoming radiation.
     - G: Soil heat flux (conductive transfer into the ground).
     - LE: Latent heat flux of evaporation (E = evaporation rate, λ = latent heat of vaporization).
     - H: Sensible heat flux (convective heat transfer between soil surface and ambient atmosphere).
     - Applications: Calculating evapotranspiration (ET), bare-soil evaporation rates, residue decomposition, and germination thermal dynamics.

3. Industrial Applications: Motors and Generators:
   • Electric Motor (DC Motor):
     - Energy Conversion: Converts ELECTRICAL energy into MECHANICAL energy.
     - Working Principle: A current-carrying conductor in a magnetic field experiences a Lorentz magnetic force:
       F = I L B sin θ (perpendicular to both conductor and magnetic flux), creating rotational torque on the rotor armature.
     - Applications: Industrial cranes, elevators, winches, rolling mills, and electric vehicles.
   • Electric Generator:
     - Energy Conversion: Converts MECHANICAL energy into ELECTRICAL energy.
     - Working Principle (Faraday's Law): Mechanical rotation forces conductors to cut across magnetic flux lines, inducing an electromotive force (emf / voltage):
       ℰ = -N (ΔΦ / Δt).
     - Factors Governing Induced EMF: Magnetic field strength (B), rotational/linear speed of conductor (v), active conductor length in field (L), and orientation angle (θ).

4. Physics in Health Sciences & Medical Imaging:
   • Nature & Types of Radiation:
     - Electromagnetic Radiation: Massless photons across the spectrum (radio waves, microwaves, IR, visible, UV, X-rays, gamma rays).
     - Particulate Radiation: High-speed subatomic particles possessing mass and kinetic energy (electrons, positrons, protons, neutrons, α-particles).
   • Ionization Mechanisms:
     - Non-Ionizing Radiation: Low-frequency, long-wavelength photons with insufficient energy to remove orbital electrons (microwaves, RF, visible light).
     - Ionizing Radiation: High-energy radiation with sufficient energy to break chemical bonds and strip orbital electrons:
       * Directly Ionizing: Fast charged particles (electrons, protons, α-particles) that deposit energy directly via Coulomb interactions with atomic electrons along their path.
       * Indirectly Ionizing: Uncharged photons or particles (X-rays, γ-rays, neutrons) that first liberate secondary charged particles via photoelectric effect, Compton scattering, or pair production, which subsequently ionize matter.
   • Diagnostic Imaging Modalities:
     - Conventional X-Ray: 2D projection imaging based on differential attenuation (absorption) through tissues of varying density (dense bone absorbs more than soft tissue).
     - Computed Tomography (CT Scan): Cross-sectional X-ray beams rotated around the patient, mathematically reconstructed into detailed 3D volumetric slices.
     - Magnetic Resonance Imaging (MRI): Utilizes Nuclear Magnetic Resonance (NMR) principles: aligns the magnetic dipole moments of hydrogen nuclei (protons) in strong static magnetic fields, perturbs them with radiofrequency (RF) pulses, and maps relaxation signals without ionizing radiation.
     - Ultrasound Imaging: Completely non-ionizing technique utilizing high-frequency acoustic waves (megahertz range) reflected at tissue boundaries.

5. Earth, Space Sciences & Renewable Energy Systems:
   • Seismology & Earth Structure:
     - Inertial seismometers record mechanical elastic waves from earthquakes:
     - P-Waves (Primary): Longitudinal compressional waves that travel fastest through both solids and liquids.
     - S-Waves (Secondary): Transverse shear waves that travel slower and CANNOT propagate through liquids (proving Earth's outer core is liquid).
   • Atmospheric Physics & Telecommunications:
     - Atmospheric thermodynamics and radiation budgets drive global climate cycles.
     - Maxwellian electromagnetic wave propagation powers satellite communications, long-range radar, and mobile networks.
   • Renewable Energy Technologies:
     - Solar Photovoltaic (PV): Direct conversion of solar photon flux into direct-current (DC) electricity via the photoelectric effect at semiconductor p-n junctions.
     - Solar Thermal: Concentrates solar irradiance via mirrors/absorbers to produce high-temperature steam that drives conventional turbine generators.
     - Wind Energy: Aerodynamic turbine blades capture the kinetic energy of moving air masses (½ m v³) to drive inductive electric generators.
     - Hydroelectric Power: Harnesses gravitational potential energy of elevated reservoir water (PE = mgh), converted to kinetic energy through penstocks to rotate hydraulic turbines coupled to electromagnetic generators.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Ideal Soil Phase Ratio: Remember the standard volumetric ratio for optimal upland soil: 50% Solids (mineral + organic), 25% Liquid (water), 25% Gas (air).
• Motor vs. Generator Duality: Electric motors convert Electrical → Mechanical energy via magnetic force F = ILB sin θ; Electric generators convert Mechanical → Electrical energy via Faraday's electromagnetic induction.
• Ionizing Radiation Classification: Charged particles (α, β, protons) are DIRECTLY ionizing via Coulomb forces; uncharged photons (X-rays, γ-rays) and neutrons are INDIRECTLY ionizing because they must first liberate secondary charged electrons.
• MRI Radiation Safety: MRI uses strong static magnetic fields and RF waves to manipulate hydrogen nuclear spins—it uses ZERO ionizing radiation, making it distinct from CT and X-ray radiography.
• Seismic S-Wave Liquid Barrier: Secondary (S) waves are shear waves and cannot propagate through fluids; their absence in the Earth's shadow zone confirms the outer core is liquid.
• Surface Energy Balance Equation: Net radiation satisfies R_n + G + LE + H = 0 (incoming radiation equals heat conducted into ground G, latent heat of evaporation LE, and convective sensible heat H).
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 108
    ),

    // =========================================================================
    // 2. MATHEMATICS (c2, Units 1 - 4)
    // =========================================================================
    ShortNote(
        id = "math-u1",
        subjectId = "c2",
        subject = "Mathematics",
        unit = "Unit 1",
        title = "Propositional Logic and Set Theory",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Mathematical logic provides the principles of valid reasoning and is used to judge the correctness of a mathematical proof based purely on its form rather than its content. This unit covers propositions, logical connectives, truth tables, rules of inference, quantifiers, and fundamental set theory.

🔍 DETAILED BREAKDOWN
1. Propositional Logic & Fundamental Connectives:
   • Proposition (Statement): A declarative sentence that has a definite truth value—either True (T) or False (F), but not both.
   • Non-propositions: Questions, commands, and exclamations do not have truth values and are not propositions.
   • The 5 Fundamental Logical Connectives:
     - Conjunction ("and", ∧): p ∧ q is True if and only if both p and q are true. Otherwise, it is false.
     - Disjunction ("or", ∨): p ∨ q (inclusive sense). False if and only if both p and q are false; true if at least one component is true.
     - Implication ("if... then", ⇒): p ⇒ q (hypothesis/antecedent p, conclusion/consequent q). False ONLY when p is true and q is false (T ⇒ F ≡ F); true in all other cases.
       * Alternative expressions: "q if p", "p only if q", "p is sufficient for q", "q is necessary for p".
     - Bi-implication ("if and only if", ⇔): p ⇔ q is True if p and q have the same truth value; false if they have different truth values.
       * Alternative expressions: "p iff q", "p is necessary and sufficient for q".
     - Negation ("not", ¬): ¬p (or -p) reverses the truth value (if p is T, ¬p is F; if p is F, ¬p is T).

2. Compound Propositions, Truth Tables & Derived Conditionals:
   • Compound Proposition: Formed by joining two or more propositions using connectives. For n propositions, a truth table contains 2ⁿ possible combinations.
   • Logical Equivalence (P ≡ Q): Two compound propositions share identical truth values for all possible combinations.
   • Derived Conditionals (Given p ⇒ q):
     - Converse: q ⇒ p
     - Inverse: ¬p ⇒ ¬q
     - Contrapositive: ¬q ⇒ ¬p (Logically equivalent to original implication: p ⇒ q ≡ ¬q ⇒ ¬p).
   • Important Logical Laws / Identities:
     - Idempotent Laws: p ≡ p ∨ p, p ≡ p ∧ p
     - Commutative Laws: p ∧ q ≡ q ∧ p, p ∨ q ≡ q ∨ p
     - Associative Laws: (p ∧ q) ∧ r ≡ p ∧ (q ∧ r), (p ∨ q) ∨ r ≡ p ∨ (q ∨ r)
     - Distributive Laws: p ∨ (q ∧ r) ≡ (p ∨ q) ∧ (p ∨ r), p ∧ (q ∨ r) ≡ (p ∧ q) ∨ (p ∧ r)
     - De Morgan's Laws: ¬(p ∧ q) ≡ ¬p ∨ ¬q, and ¬(p ∨ q) ≡ ¬p ∧ ¬q
     - Complement Law: ¬(¬p) ≡ p
   • Classifications:
     - Tautology: Always true regardless of truth values of components (e.g., p ∨ ¬p).
     - Contradiction: Always false regardless of truth values of components (e.g., p ∧ ¬p).
     - Contingency: Neither a tautology nor a contradiction.
     - Note: P ≡ Q if and only if P ⇔ Q is a tautology.

3. Open Propositions & Quantifiers:
   • Open Proposition (Predicate): A sentence containing variables whose truth value depends on values assigned to those variables (e.g., P(x)).
   • Universe of Discourse (U): Collection of all allowable values for the variable(s).
   • Quantifiers (turn open propositions into propositions):
     - Universal Quantifier (∀ - "for every", "for all", "for each"): (∀x)P(x) is true if P(x) holds for all x ∈ U. Disproven by finding a single counterexample.
     - Existential Quantifier (∃ - "there exists", "for some", "for at least one"): (∃x)P(x) is true if there is at least one x ∈ U making P(x) true.
   • Negation of Quantifiers:
     - ¬(∀x)P(x) ≡ (∃x)¬P(x)
     - ¬(∃x)P(x) ≡ (∀x)¬P(x)

4. Arguments & Validity:
   • Argument: An assertion that hypotheses/premises (p₁, p₂, ..., pₙ) yield conclusion Q, written as p₁, p₂, ..., pₙ ⊢ Q.
   • Validity: Valid if conclusion Q is true whenever all premises are simultaneously true (i.e., (p₁ ∧ p₂ ∧ ... ∧ pₙ) ⇒ Q is a tautology).
   • Common Rules of Inference:
     - Modus Ponens: p, p ⇒ q ⊢ q
     - Modus Tollens: ¬q, p ⇒ q ⊢ ¬p
     - Principle of Syllogism: p ⇒ q, q ⇒ r ⊢ p ⇒ r
     - Simplification & Conjunction rules.

5. Set Theory:
   • Set: A well-defined collection of objects called elements (x ∈ A means x belongs to A; x ∉ A means it does not).
   • Descriptions of Sets:
     - Verbal Method: Described using ordinary descriptive language.
     - Roster / Complete Listing Method: Elements listed explicitly inside braces, without repetition or regard to order.
     - Partial Listing Method: Using an ellipsis (...) when elements are numerous/infinite with a clear pattern.
     - Set-builder Method: A = {x | P(x)} (the set of all x such that P(x) is true).
   • Subsets and Power Sets:
     - Subset (B ⊆ A): Every element of B is also in A ((∀x)(x ∈ B ⇒ x ∈ A)).
     - Proper Subset (B ⊂ A): B ⊆ A and A ≠ B.
     - Power Set (𝒫(A)): Set of all subsets of A. If finite set A has n elements, it has 2ⁿ subsets and 2ⁿ - 1 proper subsets.
   • Set Operations:
     - Union (A ∪ B): Elements in A or in B (or both): {x | x ∈ A ∨ x ∈ B}
     - Intersection (A ∩ B): Elements in both A and B: {x | x ∈ A ∧ x ∈ B}. Disjoint sets: A ∩ B = ∅.
     - Difference / Relative Complement (A - B or A \ B): Elements in A but not in B.
     - Absolute Complement (A' or Aᶜ): Elements of universal set U not in A (U - A).
     - Symmetric Difference (A Δ B): (A - B) ∪ (B - A).
   • Key Set Laws:
     - De Morgan's Laws for Sets: (A ∪ B)' = A' ∩ B' and (A ∩ B)' = A' ∪ B'
     - Complement Laws: (A')' = A, A ∪ A' = U, A ∩ A' = ∅

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The conditional p ⇒ q is ONLY false when p is True and q is False. If antecedent p is False, the conditional is ALWAYS True (vacuously true)!
• A conditional statement p ⇒ q is logically equivalent ONLY to its contrapositive (¬q ⇒ ¬p), NOT to its converse or inverse.
• For a finite set with n elements: Number of Subsets = 2ⁿ, Number of Proper Subsets = 2ⁿ - 1.
• Negating a universal quantifier statement (∀) produces an existential quantifier (∃), and vice versa: ¬(∀x)P(x) ≡ (∃x)¬P(x).
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 95
    ),
    ShortNote(
        id = "math-u2",
        subjectId = "c2",
        subject = "Mathematics",
        unit = "Unit 2",
        title = "The Real and Complex Number Systems",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
This unit establishes the hierarchy and algebraic structures of numbers—from Natural Numbers (ℕ) through Integers (ℤ), Rationals (ℚ), and Reals (ℝ) to the Complex Number System (ℂ). It covers axiomatic field properties, order, decimal representations, the completeness property, polar representations, De Moivre's theorem, and extraction of roots.

🔍 DETAILED BREAKDOWN
1. The Real Number System (ℝ) Hierarchy:
   • Natural Numbers (ℕ = {1, 2, 3, ...}):
     - Closed under addition and multiplication; satisfies commutativity, associativity, and distributivity.
     - Multiplicative identity is 1 (no additive identity in ℕ).
     - Trichotomy: Exactly one holds: a > b, a < b, or a = b.
     - Divisibility: Prime (exactly two factors: 1 and itself), Composite (≥ 3 factors). 1 is NEITHER prime nor composite.
     - Fundamental Theorem of Arithmetic: Every composite number has a unique prime factorization up to order.
     - GCF: Product of common prime factors with lowest exponents (relatively prime if GCF = 1).
     - LCM: Product of all prime factors present with highest exponents.
     - Well-Ordering Principle: Every non-empty subset of ℕ has a least (smallest) element.
     - Principle of Mathematical Induction (PMI): 1) Verify base step P(1); 2) Assume P(k) is true; 3) Prove P(k+1) is true.
     - Whole Numbers: W = {0, 1, 2, ...} = ℕ ∪ {0}.
   • Integers (ℤ = {..., -2, -1, 0, 1, 2, ...}):
     - Closed under addition, subtraction, and multiplication.
     - Additive identity 0; additive inverse -a (a + (-a) = 0).
     - Inequality rule: Multiplying by negative reverses inequality (a > b and c < 0 ⇒ ac < bc).
   • Rational Numbers (ℚ = {a/b : a, b ∈ ℤ, b ≠ 0}):
     - Lowest terms when GCF(a, b) = 1.
     - Field properties: Additive inverse -a/b; multiplicative inverse (reciprocal) b/a for non-zero rational.
     - Decimal representations:
       * Terminating: Prime factors of simplified denominator are ONLY 2s and/or 5s.
       * Periodic (Repeating): Contains recurring blocks. Converted via formula d = d × [(10^(k+p) - 10^k) / (10^(k+p) - 10^k)] (k = non-repeating digits after decimal, p = period length).
   • Irrational Numbers (ℚ'): Non-terminating and non-periodic decimals (e.g., √2, π, e). Proof that √2 is irrational uses parity and coprimality contradiction.
   • Real Numbers (ℝ = ℚ ∪ ℚ'): 1-to-1 correspondence with points on the real number line.
     - Completeness Property of ℝ: Every non-empty subset of ℝ bounded above has a least upper bound (lub / supremum) in ℝ; every non-empty subset bounded below has a greatest lower bound (glb / infimum) in ℝ. (Fails in ℚ!).

2. The Complex Number System (ℂ):
   • Definition: ℂ = {z : z = x + iy, where x, y ∈ ℝ and i² = -1}.
     - Real part Re(z) = x; Imaginary part Im(z) = y.
     - Purely real if y = 0; purely imaginary if x = 0 (with y ≠ 0).
     - Equality: a + ib = c + id ⇔ a = c and b = d.
   • Arithmetic in Rectangular Form (z₁ = a + ib, z₂ = c + id):
     - Addition: (a + c) + i(b + d)
     - Subtraction: (a - c) + i(b - d)
     - Multiplication: (ac - bd) + i(ad + bc)
     - Division: (z₁/z₂) = [(ac + bd)/(c² + d²)] + i[(bc - ad)/(c² + d²)] (c² + d² ≠ 0).

3. Conjugate, Modulus & Inverses:
   • Complex Conjugate (z̄ = x - iy):
     - Reflection across real axis: z̄̄ = z.
     - z + z̄ = 2x = 2 Re(z); z - z̄ = 2iy = 2i Im(z).
     - Distributes over addition, subtraction, multiplication, and division.
   • Modulus / Norm (|z| = √(x² + y²)):
     - Geometric distance from (x, y) to the origin: |z| = |z̄| = |-z|.
     - Fundamental identity: |z|² = z · z̄.
     - Multiplicative: |z₁ · z₂| = |z₁| · |z₂|, |z₁ / z₂| = |z₁| / |z₂|.
     - Triangle Inequality: |z₁ + z₂| ≤ |z₁| + |z₂|, and |z₁ - z₂| ≥ |z₁| - |z₂|.
   • Inverses:
     - Additive Inverse: -z = -x - iy.
     - Multiplicative Inverse (z ≠ 0): z⁻¹ = 1/z = z̄ / |z|² = x/(x² + y²) - i[y/(x² + y²)].

4. Polar Form, De Moivre's Theorem & Euler's Formula:
   • Arguments:
     - General argument: arg(z) = tan⁻¹(y/x).
     - Principal Argument (Arg z = θ): Unique angle such that -π < θ ≤ π.
     - Arg(z₁ · z₂) = Arg(z₁) + Arg(z₂); Arg(z₁ / z₂) = Arg(z₁) - Arg(z₂).
   • Polar Form: z = r(cos θ + i sin θ), where r = |z| and θ = Arg z.
     - Multiplication: z₁ z₂ = r₁ r₂ [cos(θ₁ + θ₂) + i sin(θ₁ + θ₂)].
     - Division: z₁ / z₂ = (r₁ / r₂) [cos(θ₁ - θ₂) + i sin(θ₁ - θ₂)].
   • De Moivre's Formula: For any integer n:
     zⁿ = [r(cos θ + i sin θ)]ⁿ = rⁿ (cos nθ + i sin nθ).
   • Euler's Formula: e^(iθ) = cos θ + i sin θ ⇒ Exponential form: z = r e^(iθ), and zⁿ = rⁿ e^(inθ).

5. Extraction of n-th Roots of Complex Numbers:
   • For non-zero z = r(cos θ + i sin θ) = r e^(iθ), there are exactly n distinct n-th roots:
     w_k = r^(1/n) [cos((θ + 2kπ)/n) + i sin((θ + 2kπ)/n)] = r^(1/n) e^(i(θ + 2kπ)/n)
     for k = 0, 1, 2, ..., n - 1.
   • Geometric Representation: The n roots lie evenly spaced on a circle of radius r^(1/n) centered at the origin, with angular separation of 2π/n radians between adjacent roots.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The number 1 is NEITHER prime nor composite. 2 is the ONLY even prime number.
• A reduced rational a/b yields a terminating decimal if and only if prime factorization of b has only 2s and 5s (2ᵐ · 5ⁿ). Any other prime factor produces a repeating decimal.
• The Completeness Property holds in ℝ but fails in ℚ (e.g., the set {x ∈ ℚ : x² < 2} is bounded above in ℚ, but its lub √2 ∉ ℚ).
• Modulus identity |z|² = z · z̄ is the fastest way to evaluate division and find multiplicative inverse: z⁻¹ = z̄ / |z|².
• The n roots of any complex number always form vertices of a regular n-sided polygon inscribed in a circle of radius |z|^(1/n).
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 88
    ),
    ShortNote(
        id = "math-u3",
        subjectId = "c2",
        subject = "Mathematics",
        unit = "Unit 3",
        title = "Functions & Transcendental Equations",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
A function is a foundational mathematical object formalizing deterministic dependence between variables. This unit comprehensively covers relations, domain/range, algebraic operations, function composition, invertibility, polynomial and rational functions (with asymptotes), and transcendental functions (exponential, logarithmic, trigonometric, and hyperbolic).

🔍 DETAILED BREAKDOWN
1. Relations & Functions Fundamentals:
   • Cartesian Product: A × B = {(a, b) : a ∈ A, b ∈ B}. Equality: (a, b) = (c, d) ⇔ a = c and b = d.
   • Relation: Any subset R ⊆ A × B. Domain: Dom(R) = {a ∈ A : (a, b) ∈ R}; Range: Range(R) = {b ∈ B : (a, b) ∈ R} ⊆ B (Codomain).
   • Special Relations on Set A: Universal (A × A), Identity ({(a, a) : a ∈ A}), Empty (∅).
   • Inverse Relation (R⁻¹): R⁻¹ = {(b, a) : (a, b) ∈ R}. Notice Dom(R⁻¹) = Range(R) and Range(R⁻¹) = Dom(R).
   • Function (f: A → B): Relation where Dom(f) = A and each element in A maps to EXACTLY ONE element in B (if (x, y) ∈ f and (x, z) ∈ f, then y = z). Notation: y = f(x).

2. Operations, Composition & Inverses:
   • Algebraic Operations:
     - Sum, Difference, Product: Dom(f ± g) = Dom(f · g) = Dom(f) ∩ Dom(g).
     - Quotient: Dom(f / g) = [Dom(f) ∩ Dom(g)] \ {x : g(x) = 0}.
   • Composite Function (f ∘ g)(x) = f(g(x)): Dom(f ∘ g) = {x ∈ Dom(g) : g(x) ∈ Dom(f)}.
   • Function Classifications:
     - One-to-One (Injective): f(x₁) = f(x₂) ⇒ x₁ = x₂ (passes Horizontal Line Test).
     - Onto (Surjective): Range(f) = B (every element in codomain has a pre-image).
     - Bijective: Both one-to-one and onto.
   • Inverse Function (f⁻¹): Exists if and only if f is 1-to-1.
     - Dom(f⁻¹) = Range(f) and Range(f⁻¹) = Dom(f).
     - Steps: Set y = f(x), swap x and y, and solve explicitly for y = f⁻¹(x). Note: f⁻¹(x) ≠ 1/f(x).

3. Polynomials, Theorems & Rational Functions:
   • Polynomial: p(x) = aₙ xⁿ + ... + a₁ x + a₀ (aₙ ≠ 0, n ∈ ℕ ∪ {0}). Domain = ℝ; at most n real zeros and n - 1 turning points.
   • Core Polynomial Theorems:
     - Division Algorithm: p(x) = d(x) · q(x) + R(x), where deg(R) < deg(d) or R(x) = 0.
     - Remainder Theorem: Remainder when dividing p(x) by (x - r) is R = p(r).
     - Factor Theorem: (x - r) is a factor of p(x) ⇔ p(r) = 0.
     - Location Theorem (IVP): If p(a)·p(b) < 0, there is at least one real zero in (a, b).
     - Fundamental Theorem of Algebra: Every degree n ≥ 1 polynomial has at least one complex zero.
     - Linear Factorization: Factors into n linear factors p(x) = aₙ(x - r₁)...(x - rₙ) over ℂ.
     - Conjugate Roots: For real coefficients, non-real roots occur in conjugate pairs (a ± bi).
     - Rational Root Theorem: Rational zero p/q has p dividing constant term a₀ and q dividing leading coefficient aₙ.
   • Rational Functions & Asymptotes (f(x) = n(x)/d(x)):
     - Domain: {x ∈ ℝ : d(x) ≠ 0}.
     - Vertical Asymptote (VA): Line x = a where d(a) = 0 and n(a) ≠ 0 in simplified form.
     - Horizontal Asymptote (HA):
       * deg(n) < deg(d) ⇒ y = 0.
       * deg(n) = deg(d) ⇒ y = aₙ / bₙ (ratio of leading coefficients).
       * deg(n) > deg(d) ⇒ No HA.
     - Oblique (Slant) Asymptote (OA): Occurs ONLY when deg(n) = deg(d) + 1; found by quotient of division y = ax + b.

4. Exponential & Logarithmic Functions:
   • Exponent Rules: ⁿ√(aⁿ) = a if n is odd; ⁿ√(aⁿ) = |a| if n is even.
   • Exponential Function (y = bˣ, b > 0, b ≠ 1):
     - Dom = ℝ, Range = (0, ∞); y-intercept (0, 1); HA at line y = 0.
     - Increasing if b > 1; decreasing if 0 < b < 1. Natural base: eˣ (e ≈ 2.71828).
   • Logarithmic Function (y = log_b x ⇔ bʸ = x):
     - Dom = (0, ∞), Range = ℝ; x-intercept (1, 0); VA at line x = 0.
     - Laws: log_b(uv) = log_b u + log_b v; log_b(u/v) = log_b u - log_b v; log_b(uʳ) = r log_b u.
     - Change of Base: log_b x = (log_a x) / (log_a b).

5. Trigonometric & Hyperbolic Functions:
   • Angle & Coordinate Definitions: s = rθ (radians); sin θ = y/r, cos θ = x/r, tan θ = y/x.
   • Periods: sin, cos, csc, sec have period 2π; tan and cot have period π.
   • Key Trig Identities:
     - Pythagorean: sin² x + cos² x = 1, 1 + tan² x = sec² x, 1 + cot² x = csc² x.
     - Double-Angle: sin 2x = 2 sin x cos x; cos 2x = cos² x - sin² x = 2 cos² x - 1 = 1 - 2 sin² x.
     - Half-Angle: sin(x/2) = ±√[(1 - cos x)/2]; cos(x/2) = ±√[(1 + cos x)/2].
   • Hyperbolic Functions:
     - Definitions: sinh x = (eˣ - e⁻ˣ)/2 (odd), cosh x = (eˣ + e⁻ˣ)/2 (even, Range [1, ∞)), tanh x = sinh x / cosh x.
     - Identities: cosh² x - sinh² x = 1, 1 - tanh² x = sech² x, coth² x - 1 = csch² x.
     - Notice: cosh x > sinh x for all real x.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Invertibility Requirement: A function f must be one-to-one (bijective to its range) to have an inverse function f⁻¹. Always use the Horizontal Line Test!
• Holes vs. Vertical Asymptotes: Always factor and reduce rational functions first! If (x - c) cancels from both numerator and denominator, x = c is a REMOVABLE HOLE, not a vertical asymptote.
• Oblique Asymptotes exist strictly when deg(numerator) = deg(denominator) + 1. If numerator degree exceeds by 2 or more, no linear asymptote exists.
• Radical simplification trap: √(x²) = |x|, NOT simply x!
• Crucial sign difference: In circular trig, cos² x + sin² x = 1. In hyperbolic trig, cosh² x - sinh² x = 1.
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 104
    ),
    ShortNote(
        id = "math-u4",
        subjectId = "c2",
        subject = "Mathematics",
        unit = "Unit 4",
        title = "Analytic Geometry & Conic Sections",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Analytic geometry bridges algebra and Euclidean geometry via Cartesian coordinates. This unit covers line equations and division of segments, point-to-line distances, circles, conic sections (parabolas, ellipses, hyperbolas), rotation of coordinate axes, and discriminant classification of general second-degree equations.

🔍 DETAILED BREAKDOWN
1. Lines, Distance & Division of Segments:
   • Distance Formula: |PQ| = √[(x₂ - x₁)² + (y₂ - y₁)²]. Distance from origin: |OP| = √(x² + y²).
   • Division of Segment: Point R(x₀, y₀) dividing PQ in ratio |PR| : |RQ| = r₁ : r₂:
     x₀ = (x₁ r₂ + x₂ r₁) / (r₁ + r₂),  y₀ = (y₁ r₂ + y₂ r₁) / (r₁ + r₂).
     - Midpoint Formula (r₁ : r₂ = 1 : 1): M = ((x₁ + x₂)/2, (y₁ + y₂)/2).
   • Slope (m): m = (y₂ - y₁) / (x₂ - x₁) = tan θ (inclination angle 0 ≤ θ < π). Horizontal: m = 0; Vertical: slope undefined.
   • Forms of Linear Equations:
     - Point-Slope: y - y₁ = m(x - x₁)
     - Slope-Intercept: y = mx + b (b is y-intercept)
     - Intercept Form: x/a + y/b = 1 (a, b ≠ 0)
     - General Form: ax + by + c = 0 (a, b not both zero).
   • Parallel & Perpendicular Lines:
     - Parallel (ℓ₁ ∥ ℓ₂): m₁ = m₂ (or both vertical).
     - Perpendicular (ℓ₁ ⊥ ℓ₂): m₁ m₂ = -1 (or one vertical and one horizontal).
   • Point-to-Line Distance: Perpendicular distance from P(x₀, y₀) to ax + by + c = 0:
     d(P, L) = |a x₀ + b y₀ + c| / √(a² + b²). From origin: |c| / √(a² + b²).

2. Circles:
   • Standard Equation: (x - h)² + (y - k)² = r² (Center (h, k), radius r > 0). Origin: x² + y² = r².
   • General Form (x² + y² + Dx + Ey + F = 0):
     - Center: (-D/2, -E/2); Radius: r = ½√(D² + E² - 4F).
     - Classification: D² + E² - 4F > 0 (real circle); = 0 (single point / point-circle); < 0 (no real locus).
   • Tangency Condition: Perpendicular distance from center C to line ℓ:
     - d(C, ℓ) > r ⇒ No intersection.
     - d(C, ℓ) < r ⇒ Secant line (two intersection points).
     - d(C, ℓ) = r ⇒ Tangent line (radius to point of tangency is perpendicular to tangent line).

3. Parabolas:
   • Definition: Set of points equidistant from focus F and directrix line L.
   • Components: Vertex V (midpoint between focus and directrix), focal length p = |VF| > 0, Latus Rectum length = 4p.
   • Standard Equations:
     - Vertical (Opens Up): (x - h)² = 4p(y - k); Focus: (h, k + p); Directrix: y = k - p; Axis: x = h.
     - Vertical (Opens Down): (x - h)² = -4p(y - k); Focus: (h, k - p); Directrix: y = k + p; Axis: x = h.
     - Horizontal (Opens Right): (y - k)² = 4p(x - h); Focus: (h + p, k); Directrix: x = h - p; Axis: y = k.
     - Horizontal (Opens Left): (y - k)² = -4p(x - h); Focus: (h - p, k); Directrix: x = h + p; Axis: y = k.
     - At Origin (0, 0): Reduces to x² = ±4py and y² = ±4px.

4. Ellipses:
   • Definition: Set of points where sum of distances to two foci is constant: |PF'| + |PF| = 2a.
   • Relationships: Major axis = 2a, Minor axis = 2b, Focal distance = c.
     - Fundamental Relation: a² = b² + c² ⇒ c² = a² - b² (where a ≥ b).
     - Eccentricity: e = c / a = √(a² - b²) / a (0 < e < 1; as e → 0, ellipse approaches circle).
   • Standard Equations:
     - Horizontal: (x - h)²/a² + (y - k)²/b² = 1 (a > b). Foci: (h ± c, k); Vertices: (h ± a, k).
     - Vertical: (x - h)²/b² + (y - k)²/a² = 1 (a > b). Foci: (h, k ± c); Vertices: (h, k ± a).
   • General Form (Ax² + Cy² + Dx + Ey + F = 0, A, C > 0):
     - Ellipse if D²C + E²A - 4ACF > 0; degenerate point if = 0; empty if < 0.

5. Hyperbolas:
   • Definition: Set of points where absolute difference of distances to two foci is constant: ||PF'| - |PF|| = 2a.
   • Relationships: Transverse axis = 2a (connects vertices), Conjugate axis = 2b, Focal distance = c.
     - Fundamental Relation: c² = a² + b² ⇒ c = √(a² + b²) (where c > a).
     - Eccentricity: e = c / a > 1.
   • Standard Equations & Asymptotes:
     - Horizontal: (x - h)²/a² - (y - k)²/b² = 1. Asymptotes: y - k = ±(b/a)(x - h).
     - Vertical: (y - k)²/a² - (x - h)²/b² = 1. Asymptotes: y - k = ±(a/b)(x - h).

6. General Second-Degree Equations & Rotation of Axes:
   • General Form: Ax² + Bxy + Cy² + Dx + Ey + F = 0.
   • Classification Without Rotation (B = 0):
     - Parabola: AC = 0 (exactly one quadratic term is present).
     - Circle: A = C ≠ 0 (same sign and equal coefficient).
     - Ellipse: AC > 0 with A ≠ C (same sign, unequal).
     - Hyperbola: AC < 0 (opposite signs).
   • Rotation of Axes (B ≠ 0):
     - Coordinates rotate counterclockwise by θ: x = x' cos θ - y' sin θ, y = x' sin θ + y' cos θ.
     - Eliminating xy-term: cot 2θ = (A - C) / B (if A = C, θ = 45°).
   • Invariant Discriminant Test (Δ = B² - 4AC):
     - Parabola: B² - 4AC = 0
     - Ellipse / Circle: B² - 4AC < 0
     - Hyperbola: B² - 4AC > 0

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Contrast the Pythagorean relations: For an Ellipse, a² = b² + c² (a is longest, e < 1). For a Hyperbola, c² = a² + b² (c is longest, e > 1).
• In parabolas, the focal width (length of latus rectum) is always 4p.
• Tangency verification: A line is tangent to a circle if and only if the perpendicular distance from the center to the line equals the radius (d = r).
• Rotation angle: When A = C in Ax² + Bxy + Cy² + ... = 0, the rotation angle to eliminate the xy-term is always exactly θ = 45° (π/4).
• Conic discriminant invariant: B² - 4AC remains invariant under any axis rotation.
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 97
    ),

    // =========================================================================
    // 3. GENERAL PSYCHOLOGY (c3, Units 1 - 11)
    // =========================================================================
    ShortNote(
        id = "psyc-u1",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 1",
        title = "Essence of Psychology, Schools & Research Foundations",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Psychology is the scientific study of human behavior (overt, directly observable actions and reactions) and the underlying mental processes (covert, private internal experiences). Rooted in the Greek words "Psyche" (mind, soul, spirit) and "Logos" (study, knowledge, discourse), psychology is symbolized by the Greek letter Ψ (psi). As an empirical science, it relies on systematic observation and experimentation to fulfill four fundamental objectives: Description, Explanation, Prediction, and Control.

🔍 DETAILED BREAKDOWN
1. Definition & Fundamental Concepts:
   • Etymological Roots: Derived from Greek "Psyche" (mind/soul/spirit) + "Logos" (study/knowledge). Epistemologically, the study of mind/soul, symbolized by Greek letter Ψ (psi).
   • Modern Scientific Definition: The scientific study of human behavior and underlying mental processes.
   • Three Essential Components:
     - Science: Relies on empirical data, structured procedures, and systematic observation rather than intuition or common sense.
     - Behavior: All overt, outward observable actions (walking, talking, facial expressions, gestures) as well as recognizing covert mental states.
     - Mental Processes: Private, covert, internal activities that cannot be directly observed (thinking, feeling, reasoning, remembering, dreaming).

2. Four Goals of Psychology:
   • Description (What is happening?): Observing behavior objectively and recording everything about it (What? Where? To whom? Under what conditions?).
   • Explanation (Why is it happening?): Discovering the underlying causes and reasons; aids in generating hypotheses and constructing overarching theories.
   • Prediction (When will it happen again?): Forecasting the likelihood of future behaviors based on past patterns and scientific evidence.
   • Control (How can it be changed?): Modifying or shaping behavior from undesirable states (e.g., phobias, failure) to desirable ones (resilience, success), and maintaining experimental control (holding extraneous variables constant).

3. Historical Background & The Birth of Psychology:
   • Independent Science: Founded in 1879 in Leipzig, Germany, when Wilhelm Wundt established the first formal psychological laboratory.
   • Wilhelm Wundt: Recognized as the "Father of Modern Psychology."
   • Objective Introspection: Wundt's method of systematic self-examination and measurement of one's own conscious mental experiences and sensations.

4. Early Schools of Psychology:
   • Structuralism (Edward Titchener, student of Wundt):
     - Core Idea: Analyzed the conscious mind into fundamental building blocks/structures.
     - 3 Basic Elements: Sensations, feelings, and images.
     - Method: Introspection ("looking inward").
   • Functionalism (William James, author of first psychology textbook):
     - Core Idea: Influenced by Darwinian evolution; studied how the mind functions to help organisms adapt, survive, work, and play in their environment.
     - Methods: Expanded beyond introspection to mental tests, questionnaires, and behavioral observations.
   • Gestalt Psychology (Max Wertheimer & colleagues):
     - Core Idea: "The whole is greater than the sum of its parts." The mind cannot be understood by reducing it to disconnected pieces.
     - "Gestalt" translates to form, whole, configuration, or pattern; focused on perceptual organization.
   • Behaviorism (John B. Watson; later B.F. Skinner & Edward Thorndike):
     - Core Idea: Completely rejected non-observable consciousness or mental processes; restricted psychology strictly to observable, measurable behavior.
     - Key Tenets: Behaviors are conditioned reflexes learned from the environment; organisms are reactive learners; heavy reliance on animal research.
   • Psychoanalysis (Sigmund Freud, Austrian physician):
     - Core Idea: Human behavior is governed by powerful unconscious drives, repressed biological instincts, and childhood emotional conflicts.
     - Hysteria (Conversion Reaction): Physical symptoms originating from suppressed emotional turmoil.
     - The Iceberg Analogy: Conscious awareness is only the visible tip; the vast submerged mass represents the unconscious mind (suppressed urges, unresolved traumas).
     - Expressions of Unconscious: Dreams, "Freudian slips" (slips of the tongue), jokes, and accidents.
     - Methods: Free association, dream analysis, hypnosis, and clinical case studies.

5. Modern Perspectives in Psychology:
   • Psychodynamic: Modern evolution of psychoanalysis; emphasizes unconscious dynamics, internal motives, and childhood experiences.
   • Behavioral: Focuses on observable behavior shaped by environmental conditioning (rewards, reinforcers, punishments); known as the "black box" approach (input -> output).
   • Humanistic: "Third Force"; rejects determinism; emphasizes free will, subjective experience, ethical values, and self-actualization (reaching full potential).
   • Cognitive: Studies internal mental processing (reasoning, memory, problem-solving, perception, language); uses neuroimaging (PET, EEG).
   • Biological: Examines physiological/neurological bases: brain structures, nervous system, neurotransmitters, hormones, and genetics.
   • Socio-Cultural: Examines how cultural norms, social roles, expectations, and ethnic backgrounds shape thought and behavior.

6. Branches and Subfields of Psychology:
   • Developmental: Studies physical, cognitive, and psychosocial changes across the lifespan (conception to death).
   • Personality: Investigates enduring traits, individual differences, self-concept, and moral development.
   • Social: Explores social interactions, relationships, group dynamics, conformity, attitudes, and perception.
   • Cross-Cultural: Compares behavioral patterns across diverse cultures to assess universality.
   • Industrial/Organizational (I/O): Applies psychological principles to workplace efficiency, personnel selection, and job satisfaction.
   • Forensic: Applies psychological principles to criminal justice, court evaluations, and eyewitness testimony.
   • Educational: Enhances instructional methods, curriculum design, learning dynamics, and academic evaluation.
   • Health: Investigates psychological factors in promoting physical wellness, disease prevention, and stress management.
   • Clinical: Diagnoses and treats severe emotional, behavioral, and mental disorders.
   • Counseling: Assists individuals with milder personal, vocational, marital, and day-to-day adjustment problems.

7. Research Methods in Psychology:
   • Descriptive Research (observes and describes behavior without assessing causality):
     - Naturalistic Observation: Observing subjects in natural habitats without interference. (Strengths: high ecological validity; Limitations: observer effect & observer bias).
     - Case Study: In-depth exploration of a single individual or unique situation. (Strength: rich detail; Limitation: lacks generalizability).
     - Survey Method: Standardized questionnaires/interviews of large representative samples. (Strengths: fast, covert data; Limitations: sampling bias, social desirability bias).
   • Correlational Research:
     - Measures statistical association between variables (-1.00 to +1.00).
     - CRITICAL RULE: Correlation does NOT prove causation!
   • Experimental Research:
     - The ONLY scientific method capable of demonstrating direct cause-and-effect relationships.
     - Independent Variable (IV): Manipulated variable (the hypothesized cause).
     - Dependent Variable (DV): Measured response outcome (the hypothesized effect).
     - Experimental Group: Receives the experimental manipulation/treatment.
     - Control Group: Receives baseline/placebo condition for comparison.
     - Random Assignment: Eliminates preexisting participant differences.
   • Steps of Scientific Research: (1) Defining Problem -> (2) Formulating Hypothesis -> (3) Testing Hypothesis (data collection & stats) -> (4) Drawing Conclusions -> (5) Reporting Results (peer review & replication).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Wundt vs. Titchener: Wilhelm Wundt founded the first psychology lab (1879, Leipzig) and introduced introspection; Titchener brought structuralism to the US and formalized the 3 basic mental elements (sensations, feelings, images).
• Clinical vs. Counseling Psychology: Clinical deals with severe psychopathology and mental disorders; Counseling deals with milder adjustment, academic, and vocational concerns.
• IV vs. DV: IV is what the researcher manipulates/controls (cause); DV is what is measured to observe the effect.
• "The Whole is Greater than the Sum of its Parts": Always identifies Gestalt psychology (Max Wertheimer).
• Cause-and-Effect: ONLY experimental research establishes causality; correlational research only reveals relationships or associations.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 76
    ),
    ShortNote(
        id = "psyc-u2",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 2",
        title = "Sensation and Perception",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Sensation and perception serve as the foundational starting points for all psychological processes, including learning, memory, thought, communication, and emotion. Sensation is the physiological detection and transmission of raw physical energy (stimuli) by sensory receptors to the brain. Perception is the higher-order cognitive process that organizes, interprets, and translates raw sensory data into meaningful, conscious experiences. Together, they operate along an unbroken continuum.

🔍 DETAILED BREAKDOWN
1. Meanings & Continuum of Sensation and Perception:
   • Foundational Role: Sensation and perception are the prerequisites for all conscious awareness and cognition.
   • Sensation:
     - The process by which stimulation of sensory receptor cells (eyes, ears, nose, tongue, skin) generates and transmits nerve impulses to the brain.
     - Originates from a stimulus—physical energy (e.g., light waves, sound vibrations) activating sensory receptors.
     - Detects raw, uninterpreted physical qualities (e.g., color, brightness, pitch, bitterness).
   • Perception:
     - The psychological process that organizes, interprets, and translates raw sensory inputs into meaningful patterns and orders.
     - Recognizing a melody, identifying a voice, or distinguishing geometric forms is perceptual.
   • The Continuum & Prosopagnosia:
     - Sensation and perception operate seamlessly: detection triggers immediate cortical interpretation.
     - Prosopagnosia (Face Blindness): A neurological condition where visual sensation is intact (patient can see individual facial features or a tiny pin on the floor) but perceptual integration fails, making facial recognition impossible.

2. Sensory Laws & Thresholds:
   • Absolute Threshold (Limen):
     - The minimum physical energy of a stimulus that an individual can detect.
     - Operational Definition: The stimulus intensity detected 50% of the time across repeated trials.
     - Influenced by stimulus intensity and response bias (subjective cognitive readiness/expectancy).
   • Difference Threshold / Just Noticeable Difference (JND):
     - The minimum change in physical stimulation intensity required to detect that a change has occurred 50% of the time.
   • Weber's Law:
     - The amount of change required for a JND is a constant fraction (ratio) of the original stimulus intensity: ΔI / I = k.
     - Example: Weight JND is ~2%. A 50-ounce weight requires a 1-ounce change to be noticed; a 100-ounce weight requires a 2-ounce change.
     - Applies reliably to moderate stimulus intensities, but breaks down at extreme low or high limits.
   • Sensory Adaptation:
     - The physiological tendency of sensory receptors to decrease responsiveness to an unchanging, constant stimulus over time.
     - Examples: Adjusting to cold pool water, ceasing to smell room odor, ignoring train seat vibrations.
     - Evolutionary Value: Conserves sensory resources to detect novel, critical environmental changes.
     - Critical Exception: Receptors do not completely adapt to extreme or intense stimuli (e.g., severe pain, freezing cold) to ensure survival.

3. Selectivity of Perception (Attention):
   • Attention: The selective perceptual filter admitting specific stimuli into awareness while screening out competing inputs.
   • Perceptual Field: Focus (sharp, clear center of awareness) vs. Margin (dim, vague periphery). Attention shifts dynamically between them.
   • Determinants of Attention:
     - External Factors (Stimulus): Size & Intensity (louder/larger/brighter), Repetition, Novelty & Contrast (unexpected differences), Movement (triggers reflexive orienting).
     - Internal Factors (Observer): Set / Expectancy (mental readiness, e.g., expecting parent hearing phone/baby cry), Motives & Needs (hunger or thirst directing focus toward food/water).

4. Form Perception & Gestalt Organization:
   • Figure-Ground Perception:
     - The automatic visual division of the field into an object of interest (figure, appearing solid, structured, and closer) and the background (ground, formless and receding).
     - Reversible figures prove figure-ground segregation is an active, dynamic cognitive process.
     - Contours: Sharp gradients in brightness, color, or texture delineating figure from ground; disruption creates camouflage.
   • Gestalt Grouping Laws ("The whole is greater than the sum of its parts"):
     - Law of Proximity (Nearness): Elements close together in space or time are grouped into units.
     - Law of Similarity: Elements sharing physical characteristics (color, shape, size) are grouped together.
     - Law of Good Figure (Symmetry / Prägnanz): Stimuli are organized into symmetrical, stable, and orderly configurations.
     - Law of Continuity: Lines and curves are perceived as continuing smoothly along an established path rather than making abrupt angular turns.
     - Law of Closure: Perceptual filling in of gaps in incomplete figures to perceive a unified whole.

5. Depth Perception:
   • Translates 2D retinal projections (proximal stimuli) into 3D spatial representations (distal stimuli) to judge distance.
   • Binocular Cues (Require both eyes):
     - Retinal Disparity: Anatomical separation of the eyes produces slightly different retinal images; closer objects produce greater disparity, computed by cortical neurons.
     - Convergence: Neuromuscular tension from inward rotation of eyes when focusing on nearby objects; greater muscular strain signals proximity (disrupted by alcohol).
   • Monocular Cues (Require only one eye):
     - Accommodation: Muscular feedback from the crystalline lens changing shape; greater curvature signals close proximity.
     - Motion Parallax: During observer motion, nearby stationary objects appear to move backward rapidly, while distant objects move slowly forward.
     - Pictorial Cues (Static artistic depth indicators):
       * Interposition (Overlap): Partially obscured object is perceived as farther.
       * Relative Size: Smaller retinal image perceived as more distant.
       * Linear Perspective: Parallel lines (e.g., railroad tracks) converge toward a vanishing point.
       * Elevation (Relative Height): Objects higher in the visual plane appear more distant.
       * Shading & Shadows: Highlights/shadows provide 3D depth since light comes from above.
       * Aerial Perspective: Distant objects appear hazier, bluer, and less distinct due to atmospheric scattering.
       * Texture Gradient: Coarse, detailed textures appear nearby; dense, fine, uniform textures appear distant.

6. Perceptual Constancies:
   • Recognizing objects as stable and unchanging despite continuous fluctuations in retinal images:
     - Size Constancy: Perceiving true physical size regardless of distance (shrinking retinal image interpreted as greater distance).
     - Shape Constancy: Recognizing geometric shape despite changing viewing angles (e.g., open door remains rectangular though retina receives a trapezoid).
     - Brightness / Lightness Constancy: Perceiving constant brightness under varying illumination, evaluated relative to surrounding objects.

7. Perceptual Illusions & ESP:
   • Perceptual Illusions: Distorted mental perceptions resulting from misapplied or misinterpreted sensory cues.
     - Moon Illusion: Moon appears larger on the horizon than at zenith due to horizon depth cues, despite identical retinal size.
     - Müller-Lyer Illusion: Identical lines appear unequal due to inward- vs. outward-facing arrowheads (perspective/corner cues).
   • Extrasensory Perception (ESP): Purported perception without sensory organ involvement (telepathy, clairvoyance, precognition). Not accepted in scientific psychology due to lack of reliable, replicable empirical evidence.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Sensation vs. Perception: Sensation is physiological/detection (receptor activation); Perception is cognitive/interpretation (meaning and organization).
• Absolute Threshold vs. Difference Threshold: Absolute threshold is the minimum energy detected 50% of the time; Difference threshold (JND) is the minimum change detected 50% of the time.
• Weber's Law: ΔI / I = k (the ratio, not the absolute amount, remains constant).
• Prosopagnosia: Normal sensation + impaired perception.
• Retinal Disparity vs. Convergence: Both are binocular; disparity is visual/optical (retinal image difference), convergence is neuromuscular (eye muscle inward pull).
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 82
    ),
    ShortNote(
        id = "psyc-u3",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 3",
        title = "Theories of Learning & Their Applications",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Learning is defined as any relatively permanent change in behavior, knowledge, or mental capability resulting from experience, practice, or environmental interactions. It explicitly excludes behavioral changes caused by biological maturation, fatigue, illness, or psychoactive substances. Psychological theories explain learning through behavioral connections (stimulus-response associations), social observational modeling, and internal cognitive restructuring.

🔍 DETAILED BREAKDOWN
1. Behavioral Theories of Learning:
   • Focuses on observable, measurable relationships between environmental stimuli (S) and behavioral responses (R).

2. Classical Conditioning (Ivan Pavlov - Learning by Association):
   • Process whereby a neutral stimulus acquires the power to evoke an involuntary, reflexive response after paired association with an unconditioned stimulus.
   • Five Core Elements:
     - Unconditioned Stimulus (UCS): Naturally triggers an innate, unlearned physiological reflex (e.g., food/meat powder).
     - Unconditioned Response (UCR): Innate, unlearned reflex triggered automatically by the UCS (e.g., salivation to food).
     - Neutral Stimulus (NS): Stimulus that does not evoke the target reflex prior to training (e.g., bell or tone).
     - Conditioned Stimulus (CS): Previously neutral stimulus that, via repeated contiguous pairing with UCS, elicits a response (e.g., bell sound alone).
     - Conditioned Response (CR): Learned response elicited by the CS alone following conditioning (e.g., salivation to bell).
   • Three Conditioning Phases:
     - Before: NS (Bell) -> No Salivation; UCS (Meat) -> UCR (Salivation).
     - During: NS (Bell) + UCS (Meat) -> UCR (Salivation).
     - After: CS (Bell) -> CR (Salivation).
   • Core Conditioning Principles:
     - Stimulus Generalization: Eliciting the CR to stimuli physically similar to the original CS (e.g., responding to chimes/phone rings).
     - Stimulus Discrimination: Differentiating between the original CS and other similar stimuli, responding exclusively to the trained cue.
     - Extinction: Gradual weakening and cessation of the CR when the CS is repeatedly presented without the reinforcing UCS.
     - Spontaneous Recovery: Sudden reappearance of an extinguished CR after a rest interval without further training (typically with lower intensity).

3. Operant Conditioning (B.F. Skinner - Learning by Consequences):
   • Voluntary behaviors are strengthened or weakened by the environmental consequences that immediately follow them. Organisms "operate" on their environment to produce outcomes.
   • Consequences of Behavior:
     - Neutral Consequence: Environmental outcome that does not alter future probability of the response.
     - Reinforcement: ANY consequence that INCREASES the likelihood that the preceding behavior will recur.
       * Primary Reinforcers: Biologically innate rewards requiring no training (food, water, pain relief).
       * Secondary (Conditioned) Reinforcers: Acquired rewards learned through association with primary reinforcers (money, praise, high grades, tokens).
       * Positive Reinforcement: Adding a desirable stimulus to increase target behavior (e.g., praise, wages, awards).
       * Negative Reinforcement: Removing/terminating an unpleasant, aversive stimulus to increase target behavior.
         > Escape Learning: Performing a response that terminates an active unpleasant stimulus (e.g., taking aspirin to end a headache).
         > Avoidance Learning: Performing a response that prevents a predicted aversive stimulus from occurring (e.g., buckling seatbelt to prevent warning chime).
     - Punishment: ANY consequence that DECREASES the likelihood that the preceding behavior will recur.
       * Positive Punishment (Application): Administering an unpleasant event to decrease behavior (e.g., reprimands, fines).
       * Negative Punishment (Removal): Withdrawing a valued privilege or desirable item to decrease behavior (e.g., loss of phone privileges, grounding, time-out).
   • The Four Operant Contingencies Grid:
     - Add Stimulus to Increase Behavior -> Positive Reinforcement (Reward added).
     - Remove Stimulus to Increase Behavior -> Negative Reinforcement (Aversive stimulus removed).
     - Add Stimulus to Decrease Behavior -> Positive Punishment (Unpleasant event applied).
     - Remove Stimulus to Decrease Behavior -> Negative Punishment (Privilege/pleasant item removed).
   • Schedules of Reinforcement:
     - Continuous Reinforcement: Reinforcing every single response; optimal for rapid initial acquisition.
     - Intermittent / Partial Reinforcement: Reinforcing only certain responses; produces high resistance to extinction:
       * Fixed-Ratio (FR): Reinforcement delivered after a set, fixed number of responses (e.g., factory piecework).
       * Variable-Ratio (VR): Reinforcement delivered after an unpredictable, varying average number of responses (e.g., slot machines, gambling). Yields highest response rates and highest resistance to extinction!
       * Fixed-Interval (FI): Reinforcement available for first response after a set elapsed time period (e.g., cramming before fixed weekly exams; scalloped response curve).
       * Variable-Interval (VI): Reinforcement available for first response after varying, unpredictable time intervals (e.g., unannounced pop quizzes); produces slow, steady responses.
   • Shaping (Successive Approximations): Reinforcing incremental steps that get progressively closer to the final complex target behavior.
   • Limitations of Punishment: Ineffective when inconsistent; triggers fear, resentment, or aggression; suppresses behavior only in the presence of the punisher; teaches what NOT to do without demonstrating desirable replacement behaviors.

4. Social Learning Theory / Observational Learning (Albert Bandura):
   • Behavior is acquired socially by observing, retaining, and imitating the actions of external models.
   • Three Forms of Reinforcement:
     - Direct Reinforcement: Concrete rewards given directly to the observer for imitating the model.
     - Vicarious Reinforcement: Behavioral change from witnessing a model being rewarded or punished for their actions.
     - Self-Reinforcement: Internal self-evaluation yielding personal feelings of pride, competence, and satisfaction.
   • Four Essential Conditions for Modeling:
     1. Attention: Actively perceiving and focusing on the model's significant actions.
     2. Retention: Storing mental representations of the behavior via imagery or verbal rehearsal.
     3. Motor Reproduction: Physical and developmental capability to execute the stored mental patterns.
     4. Motivation: Anticipating an incentive, reward, or goal to demonstrate the learned response.
   • Educational Applications: Prosocial teacher modeling, peer tutoring building student self-efficacy, and self-regulation training.

5. Cognitive Learning Theories:
   • Learning explained through internal mental processing, mental representations, and cognitive reorganization rather than passive stimulus-response bonds.
   • Latent Learning & Cognitive Maps (Edward Tolman):
     - Learning that occurs without explicit reinforcement and remains hidden until incentives encourage demonstration.
     - Tolman & Honzik's 1930 Maze Study: Unreinforced rats formed internal mental representations (cognitive maps) of the maze layout; when food was introduced on day 11, their performance instantly matched rats rewarded continuously from day 1.
   • Insight Learning (Wolfgang Köhler / Gestalt Psychology):
     - Cognitive problem-solving through a sudden perceptual restructuring of environmental relationships.
     - Initial unproductive attempts followed by a sudden realization of the complete solution (the "Aha!" experience).
     - Insight solutions are retained long-term and readily transferred to solve related novel problems.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Negative Reinforcement vs. Punishment: Reinforcement ALWAYS increases behavior; punishment ALWAYS decreases behavior. Negative reinforcement is NOT punishment—it increases behavior by removing discomfort.
• Involuntary vs. Voluntary: Classical conditioning governs involuntary reflexes (salivation, fear); Operant conditioning governs voluntary, emitted behaviors.
• Reinforcement Schedules: Variable-Ratio (VR) produces the highest rate of responding and is the most resistant to extinction.
• Latent Learning proves that learning (internal mental acquisition) can occur without external reinforcement, whereas reinforcement motivates behavioral performance.
• Insight Learning is characterized by sudden perceptual restructuring ("Aha!" moment), not gradual trial-and-error.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 85
    ),
    ShortNote(
        id = "psyc-u4",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 4",
        title = "Memory Systems, Retrieval & Theories of Forgetting",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Memory is the cognitive capacity to encode, store, and retrieve information, skills, and personal experiences across time. It serves as an internal bridge connecting an individual's past to their present cognitive functioning. According to the Atkinson–Shiffrin multi-store model, memory operates as an integrated three-stage information-processing system: Sensory Memory, Short-Term (Working) Memory, and Long-Term Memory. Forgetting is the inability to access or recover previously stored information due to encoding failure, trace decay, interference, displacement, motivated repression, or retrieval cue absence.

🔍 DETAILED BREAKDOWN
1. Information-Processing Stages of Memory:
   • Encoding: Transforming raw sensory input into neural representational codes that the brain can process and retain.
   • Storage: Maintaining and preserving the encoded memory trace (engram) over time within neural networks.
   • Retrieval: Accessing, locating, and recovering stored information from memory into conscious awareness when needed.
   • Note: Forgetting can stem from breakdown at any stage (encoding failure, storage degradation, or retrieval cue failure).

2. The Three Memory Stores (Atkinson–Shiffrin Model):
   • Sensory Memory (Sensory Register):
     - Initial entry portal capturing an exact, unanalyzed replica of environmental sensory impressions.
     - Iconic Memory: Visual sensory buffer holding visual/photographic impressions for ~0.5 to 1 second.
     - Echoic Memory: Auditory sensory buffer holding acoustic traces for ~2 seconds.
     - High storage capacity, but rapid loss via decay unless selected by attention.
   • Short-Term Memory (STM) / Working Memory:
     - Conscious mental workspace holding actively processed information.
     - Active Processing: Data remains only as long as it is consciously manipulated or rehearsed.
     - Rapid Accessibility: Immediately available for ongoing cognitive tasks (problem-solving, reasoning, speech).
     - Temporal Sequence: Preserves the sequential order of incoming items.
     - Capacity: George Miller's "magic number 7 ± 2" discrete items (5 to 9 bits).
     - Duration: Approximately 30 seconds without active rehearsal.
     - Chunking: Grouping smaller, isolated bits of information into meaningful, higher-order units (chunks) to expand capacity (e.g., memorizing phone digits as groups).
     - Rehearsal Modes:
       * Maintenance Rehearsal: Rote mental repetition to prevent immediate decay and extend STM duration.
       * Elaborative Rehearsal: Deep processing linking new information to established knowledge in long-term memory for permanent storage.
   • Long-Term Memory (LTM):
     - Permanent, vast storehouse with virtually unlimited storage capacity and indefinite duration.
     - Declarative (Explicit) Memory (conscious, intentional, verbally communicable recollection):
       * Episodic Memory: Autobiographical recollections of personally experienced, context-specific events (e.g., your first day at university, what you ate for breakfast).
       * Semantic Memory: Impersonal, generalized factual knowledge of the world, vocabulary, mathematical rules, and abstract concepts.
     - Non-Declarative (Implicit) Memory (influences behavior without conscious recollection):
       * Procedural Memory: Motor skills, automated habits, and "how-to" physical routines (e.g., swimming, cycling, typing).

3. The Serial Position Effect:
   • The position of an item in an ordered list determines its recall probability, producing a characteristic U-shaped retention curve:
   • Primacy Effect: Superior recall of items at the beginning of a sequence; explained by greater rehearsal time enabling transfer to LTM.
   • Recency Effect: Superior recall of items at the end of a sequence; explained by terminal items actively lingering in STM at the moment of recall.
   • Middle Items: Lowest recall efficiency; crowded out by limited STM capacity and vulnerable to both proactive and retroactive interference.

4. Factors Influencing Memory Efficiency:
   • Ability to Retain: Quality and strength of neurobiological memory traces established during learning.
   • Physical Health & Maturity: Sound physical wellness, age appropriateness, and mature neurological organization.
   • Will to Remember & Interest: Conscious motivation and active curiosity yield deeper cognitive processing and stronger recall.
   • Intelligence & Learning Speed: Higher intellectual capability and faster learning support more efficient encoding.
   • Meaningfulness of Material: Semantically meaningful concepts are retained far longer than abstract or nonsense syllables.
   • Overlearning: Continuing intentional study and active practice beyond initial mastery strengthens retention and retrieval durability.
   • Rest & Sleep: Adequate sleep taken immediately after study minimizes waking interference and supports memory consolidation.

5. Meaning & Ebbinghaus Curve of Forgetting:
   • Forgetting: The inability to retrieve, recognize, or recall information previously encoded in long-term storage.
   • Hermann Ebbinghaus's Pioneering Research (1885):
     - Tested himself with lists of nonsense three-letter syllables (e.g., FIW, BOZ) to eliminate semantic bias.
     - The Forgetting Curve: Forgetting is extremely rapid immediately following initial learning (especially within the first hour); after ~9 hours, memory loss levels off into a gradual, stable plateau.
     - Relearning Advantage: Relearning previously studied material is significantly faster than initial acquisition (savings score).

6. Theoretical Models of Forgetting:
   • Decay Theory (Trace Decay): Physical memory traces (engrams) in the brain erode passively over time if not regularly refreshed. Explains sensory and unrehearsed STM decay, but poorly accounts for LTM.
   • Interference Theory: Competing memories collide and disrupt recall:
     - Proactive Interference: OLD learning disrupts and interferes with retrieval of NEW information (e.g., calling new roommate by previous roommate's name).
     - Retroactive Interference: NEW learning disrupts and interferes with recall of PREVIOUSLY learned material (e.g., learning a new phone number makes you forget your old one).
   • Displacement Theory: Incoming items knock out and displace older items when capacity limits are exceeded; exclusively explains STM loss (7 ± 2 limit).
   • Motivated Forgetting (Freudian Repression): Unconsciously pushing anxiety-provoking, painful, or traumatic memories into the unconscious as a psychological defense mechanism.
   • Cue-Dependent Forgetting (Retrieval Failure): Memory remains intact in LTM but cannot be located because appropriate retrieval cues are absent.
     - Context-Dependent Memory: Retrieval is enhanced when tested in the same environmental/physical setting where encoding occurred.
     - State-Dependent Memory: Retrieval is improved when an individual's internal physiological or emotional state matches that present during encoding.

7. Evidence-Based Strategies for Enhancing Memory:
   • Attention & Deliberate Focus: Eliminates divided attention to ensure robust initial sensory encoding.
   • Elaborative Encoding: Connect concepts through visual, verbal, and conceptual associations.
   • Add Meaning & Personal Relevance: Link abstract ideas to personal experiences and existing knowledge frameworks.
   • Distributed Practice & Adequate Sleep: Space study sessions over intervals; sleep facilitates neural consolidation.
   • Overlearning: Continue review beyond initial 100% mastery to reinforce retrieval pathways.
   • Active Self-Testing: Practice retrieval testing rather than passive rereading (the testing effect).

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • Proactive vs. Retroactive Interference:
     - Proactive: P = Prior/Past interferes with Present/New.
     - Retroactive: R = Recent/New interferes with Retro/Old.
   • Primacy vs. Recency Effect: Primacy reflects LTM transfer (rehearsal time); Recency reflects active presence in STM. If recall is delayed by 30 seconds with distraction, the Recency Effect disappears!
   • Iconic (~0.5s visual) vs. Echoic (~2s auditory) Sensory Memory.
   • Context-Dependent (external environment) vs. State-Dependent (internal mood/physiological state) retrieval.
   • Ebbinghaus Curve: Most forgetting occurs rapidly within the first hour of learning.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 89
    ),
    ShortNote(
        id = "psyc-u5",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 5",
        title = "Motivation, Conflict of Motives & Theories of Emotion",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Motivation refers to the internal and external forces that start, direct, and sustain goal-directed activities to satisfy physical or psychological needs and wants (derived from Latin "movere" = to move). Emotion is the affective feeling aspect of consciousness characterized by physiological autonomic arousal, overt expressive behavior, and subjective cognitive appraisal. Human action is driven by the dynamic interplay between internal biological needs (push factors), external incentives (pull factors), and emotional evaluations.

🔍 DETAILED BREAKDOWN
1. Definition & Types of Motivation:
   • Etymology: Latin "movere" (to move). Explains why an organism acts, persists, and directs energy toward a specific goal.
   • Intrinsic Motivation: Driven by internal satisfaction, enjoyment, or personal curiosity. The activity itself is inherently rewarding without external payoff.
   • Extrinsic Motivation: Driven by external reinforcements, tangible outcomes, or incentives outside the person (e.g., grades, money, awards, praise, avoiding punishment).

2. Approaches and Theories of Motivation:
   • Instinct Approaches: Behavior is governed by innate, unlearned biological predispositions (e.g., flight, reproduction, maternal care). Highlights genetic and evolutionary roots.
   • Drive-Reduction Approaches (Clark Hull):
     - Need: A biological requirement for essential survival resources (food, water).
     - Drive: State of physiological arousal and psychological tension arising from unfulfilled needs, motivating corrective behavior to reduce tension.
     - Primary Drives: Unlearned biological survival needs (hunger, thirst).
     - Secondary (Acquired) Drives: Learned through social conditioning (money, status, social approval).
     - Homeostasis: The biological thermostat maintaining stable internal physiological equilibrium; imbalances trigger corrective drives.
     - Limitation: Cannot explain sensation-seeking or eating when full.
   • Arousal Approaches: Organisms are motivated to achieve and maintain an optimal level of physiological/neurological stimulation (reducing excessive stress, seeking novelty when understimulated).
   • Incentive Approaches: Behavior is "pulled" by desirable external environmental goals/rewards (incentives), contrasting with the internal biological "push" of drives.
   • Cognitive Approaches: Motivation is governed by mental representations: expectations of success, perceived value of outcomes, and self-efficacy judgments.
   • Humanistic Approach (Maslow's Hierarchy of Needs):
     - Pyramid of 5 developmental levels; lower deficiency needs (D-needs) must be partially satisfied before pursuing higher growth needs (B-needs):
       1. Physiological Needs: Fundamental survival (food, water, sleep, oxygen).
       2. Safety Needs: Physical security, stability, order, freedom from fear.
       3. Love & Belongingness: Social integration, affectionate relationships, group acceptance.
       4. Esteem Needs: Self-respect, competence, mastery, social recognition, prestige.
       5. Self-Actualization: Peak tier; realizing one's fullest potential, creativity, and self-fulfillment.

3. Motivational Conflicts & Frustration:
   • Approach–Approach Conflict: Choosing between two equally desirable, positive alternatives (e.g., choosing between two appealing vacations; lowest stress).
   • Avoidance–Avoidance Conflict: Choosing between two equally undesirable, negative alternatives (e.g., taking an unappealing job or facing unemployment; "between a rock and a hard place").
   • Approach–Avoidance Conflict: A single goal possesses both attractive benefits and negative drawbacks (e.g., accepting a great promotion that requires exhausting relocation; causes vacillation).
   • Multiple Approach–Avoidance Conflict: Choosing between two or more options, each offering a complex mixture of positive advantages and negative drawbacks (most common in real-world decisions).

4. Nature & Tripartite Elements of Emotion:
   • Emotion: Acute, stimulus-specific feeling state with three interrelated components:
     1. Physiological Component: Autonomic arousal triggered by the Sympathetic Nervous System (accelerated heart rate, rapid respiration, pupil dilation, sweating) modulated by the Amygdala.
     2. Behavioral Component: Outward physical displays communicating emotional states (facial expressions, posture, vocal tone, body language). Paul Ekman identified six universally recognized facial expressions: Happiness, Sadness, Fear, Anger, Surprise, and Disgust.
     3. Subjective (Cognitive) Component: Conscious labeling, cognitive appraisal, and mental interpretation of the internal state, informed by memories and context.

5. Major Classical Theories of Emotion:
   • James-Lange Theory (William James & Carl Lange):
     - Emotion is the cognitive consequence of physiological arousal.
     - Sequence: Stimulus -> Bodily Arousal -> Conscious Emotion ("I am afraid because my heart is racing and I am running").
   • Cannon-Bard Theory (Walter Cannon & Philip Bard):
     - Visceral bodily changes are too slow and non-specific to cause distinct emotions.
     - Sequence: Stimulus -> Thalamus sends simultaneous, independent signals to the Cerebral Cortex (subjective feeling) and Autonomic Nervous System (physiological arousal).
     - Arousal and emotional experience occur at the exact same time.
   • Schachter-Singer Two-Factor Theory (Cognitive Arousal Theory):
     - Emotion requires BOTH general physiological arousal AND cognitive appraisal/labeling based on situational context.
     - Sequence: Stimulus -> Physiological Arousal -> Cognitive Interpretation of Surrounding Clues -> Felt Emotional Experience.

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • Push vs. Pull: Drives PUSH internally (e.g., hunger pains); Incentives PULL externally (e.g., aroma of fresh pizza).
   • Maslow's Levels: Physiological -> Safety -> Belongingness -> Esteem -> Self-Actualization.
   • Emotion Theory Distinctions:
     - James-Lange: Physiological arousal comes FIRST, causing emotion.
     - Cannon-Bard: Physiological arousal and emotional experience occur SIMULTANEOUSLY via the thalamus.
     - Schachter-Singer: Physiological arousal + Cognitive appraisal of external context = Emotion.
   • Conflict Types: Avoidance-Avoidance is the most stressful and causes avoidance/freezing; Approach-Avoidance creates back-and-forth hesitation.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 92
    ),
    ShortNote(
        id = "psyc-u6",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 6",
        title = "Theories of Personality (Psychoanalytic, Trait & Humanistic)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Personality refers to the unique, relatively enduring pattern of thoughts, emotional reactions, motives, and overt actions that characterize how an individual interacts with the world. Originating from the Latin word "persona" (theatrical masks worn by ancient actors), personality encompasses both innate biological traits and socially acquired styles of adaptation. Modern personality psychology is anchored by three prominent theoretical paradigms: Sigmund Freud's Psychoanalytic Theory, the Trait Perspective (The Big Five / OCEAN), and Carl Rogers' Humanistic Theory.

🔍 DETAILED BREAKDOWN
1. Meaning & Critical Distinctions of Personality:
   • Etymology: Derived from Latin "persona" (actors' masks).
   • Definition: The unique, relatively consistent pattern of thinking, feeling, and behaving that distinguishes an individual across situations and time.
   • Critical Distinctions:
     - Personality vs. Character: Character involves moral, ethical, and value evaluations of behavior (e.g., integrity, honesty, decency).
     - Personality vs. Temperament: Temperament refers to the raw, innate, biological, and physiological baseline dispositions present from birth (e.g., emotional reactivity, activity level, irritability).
     - Relationship: Temperament (biological substrate) and character (learned values) both feed into the overarching construct of personality.

2. Psychoanalytic Theory of Personality (Sigmund Freud):
   • Personality is forged from the continuous internal conflict between primitive biological animal impulses and internalized societal restraints.
   • Structural Model of the Psyche:
     - Id ("It"):
       * Oldest, fully unconscious, amoral core present at birth.
       * Houses instinctual biological drives (libido/Eros and aggression/Thanatos).
       * Operates on the Pleasure Principle: Demands immediate gratification to alleviate tension, ignoring logic, danger, and social morality ("If it feels good, do it").
     - Ego ("I"):
       * Rational, realistic executive branch that develops out of the id during early infancy.
       * Operates on the Reality Principle: Satisfies id demands through realistic, safe, and socially acceptable methods ("If it feels good, do it, but only if you can get away with it safely").
     - Superego ("Over the Self"):
       * The moral center and internalized conscience that develops during early childhood as parental and societal values are absorbed.
       * Ego-Ideal: Standards of approved, virtuous behavior inspiring pride and self-worth.
       * Conscience: Internalized rules producing guilt, remorse, and moral anxiety when violated.
   • Ego Defense Mechanisms (Unconscious tactics deploying reality distortion to cope with anxiety):
     - Repression: Involuntarily pushing threatening or traumatic thoughts/impulses into the unconscious mind (the foundation of all defense mechanisms).
     - Denial: Refusing to acknowledge, perceive, or accept an objective, painful reality (e.g., refusing to accept a severe medical diagnosis).
     - Regression: Reverting to immature, childlike behaviors typical of earlier developmental security (e.g., temper tantrums or thumb-sucking under stress).
     - Rationalization: Formulating plausible, socially acceptable, logical excuses to justify questionable conduct or failures.
     - Displacement: Redirecting aggressive or sexual impulses from the original threatening target onto a safer, less powerful substitute (e.g., yelling at a sibling after being scolded by a parent).
     - Projection: Attributing one's own unacceptable impulses, flaws, or hostile motives onto others (e.g., an unfaithful partner suspecting their spouse of infidelity).
     - Reaction Formation: Expressing conscious feelings and behaviors that are the diametrical opposite of unconscious, unacceptable desires.
     - Sublimation: Channeling socially unacceptable instinctual drives into productive, culturally praised endeavors (e.g., aggressive urges channeled into contact sports or surgical careers).
     - Mental Health Note: Defense mechanisms are universal and healthy for acute coping, but chronic or excessive reliance distorts reality and impairs adjustment.

3. Trait Theory of Personality & The Five-Factor Model:
   • Describes personality as a constellation of enduring, measurable characteristics (traits) that direct behavior across varied situations.
   • Three Core Assumptions:
     1. Stability: Traits remain relatively stable, consistent, and predictable across the lifespan.
     2. Cross-Situational Consistency: Traits influence behavior consistently across diverse settings.
     3. Individual Differences: People vary quantitatively in the degree to which they possess each trait.
   • The Big Five Model ("OCEAN" Dimensions):
     - Openness to Experience (O):
       * High: Curious, imaginative, creative, open-minded, appreciative of art/novelty.
       * Low: Conventional, pragmatic, down-to-earth, prefers routine and familiar tradition.
     - Conscientiousness (C):
       * High: Organized, dependable, self-disciplined, methodical, punctual, goal-directed.
       * Low: Disorganized, careless, spontaneous, unreliable, prone to procrastination.
     - Extraversion (E):
       * High: Outgoing, sociable, enthusiastic, assertive, energized by social stimulation.
       * Low (Introverted): Reserved, reflective, solitary, quiet, prefers intimate settings.
     - Agreeableness (A):
       * High: Trusting, cooperative, empathetic, altruistic, compassionate, warm.
       * Low: Cynical, competitive, suspicious, argumentative, irritable.
     - Neuroticism (N) / Emotional Stability:
       * High: Emotionally reactive, anxious, insecure, moody, easily stressed.
       * Low: Emotionally stable, calm, resilient under pressure, even-tempered.

4. Humanistic Theory of Personality (Carl Rogers):
   • "Third Force" rejecting psychoanalytic biological determinism and behavioral mechanical conditioning. Emphasizes innate human goodness, free will, subjective experience, and self-direction.
   • Core Concepts:
     - Self-Actualizing Tendency: Innate biological striving to fulfill one's unique potential, capabilities, and talents.
     - Self-Concept: The organized, consistent set of perceptions and beliefs that a person holds about their identity, worth, and competence.
     - Real Self vs. Ideal Self:
       * Real Self: One's actual perception of current traits, capabilities, and behaviors.
       * Ideal Self: The standard of who one aspires to be or feels one ought to be.
     - Congruence vs. Incongruence:
       * Congruence: Close alignment between Real Self and Ideal Self, producing emotional harmony, psychological health, and self-worth.
       * Incongruence: Wide divergence between Real Self and Ideal Self, generating anxiety, self-doubt, defensiveness, and neurosis.
   • Conditions of Regard:
     - Positive Regard: Warmth, approval, acceptance, and love from significant others.
     - Conditional Positive Regard: Acceptance granted only when the individual satisfies specific expectations or conforms to parental standards ("I love you only if you succeed"). Breeds incongruence.
     - Unconditional Positive Regard: Complete, non-judgmental love, acceptance, and respect given freely without prerequisites. Essential for healthy self-actualization.
   • The Fully Functioning Person: An individual experiencing high congruence who is actively moving toward self-actualization; characterized by openness to experience, existential living (present-focused), organismic trusting, and creative autonomy.

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • Personality vs. Temperament: Temperament is biological/innate from birth; Character represents moral/ethical values.
   • Id, Ego, Superego Principles:
     - Id = Pleasure Principle (immediate gratification).
     - Ego = Reality Principle (rational executive mediation).
     - Superego = Moral Imperative (Ego-ideal gives pride; Conscience gives guilt).
   • Projection vs. Displacement: Projection attributes one's OWN flaws onto someone else ("You are angry, not me"); Displacement redirects emotion to a SAFER substitute (kicking the dog).
   • Big Five Mnemonic: OCEAN (Openness, Conscientiousness, Extraversion, Agreeableness, Neuroticism).
   • Rogers' Congruence: Psychological wellness = overlap between Real Self and Ideal Self fostered by Unconditional Positive Regard.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 94
    ),
    ShortNote(
        id = "psyc-u7",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 7",
        title = "Psychological Disorders & Treatment Techniques",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
A psychological disorder is a clinically significant disturbance in an individual's cognition, emotional regulation, or behavior that causes personal distress and impairs occupational, academic, or social functioning. Psychopathology is the scientific study of psychological disorders, investigating their etiology (causes), symptoms, manifestations, and treatment. Clinicians evaluate psychological abnormalities through three primary diagnostic pillars (Abnormality/Deviance, Maladaptiveness/Dysfunction, and Personal Distress) and deploy evidence-based psychotherapeutic and pharmacological interventions.

🔍 DETAILED BREAKDOWN
1. Nature & Three Core Criteria of Abnormality:
   • Abnormality (Deviance): Behavior deviating significantly from statistical norms or cultural/social/legal standards. Must always be interpreted within cultural context.
   • Maladaptiveness (Dysfunction): Behaviors disrupting everyday functioning, safety, academic success, work, or healthy social relationships.
   • Personal Distress (Subjective Discomfort): Internal emotional suffering (severe anxiety, depression, feelings of hopelessness). Note: not present in all disorders (e.g., mania or antisocial personality disorder).

2. Theoretical Perspectives on Etiology:
   • Biological Perspective: Physical illness or neural pathology. Emphasizes genetics, structural brain abnormalities, and neurotransmitter imbalances (e.g., dopamine excess linked to schizophrenia; serotonin/norepinephrine deficits linked to depression).
   • Psychological Perspectives:
     - Psychoanalytic (Freud): Unconscious conflicts between id, ego, and superego; unresolved childhood emotional traumas producing conversion symptoms and chronic anxiety.
     - Behavioral / Learning: Disorders acquired through normal learning principles:
       * Classical Conditioning: Learned pairings producing irrational phobias.
       * Operant Conditioning: Negative reinforcement maintaining avoidance behaviors (e.g., agoraphobia avoidance reducing immediate anxiety).
       * Modeling / Observational: Imitating dysfunctional familial behaviors.
     - Cognitive: Psychological distress stems from faulty perception, distorted schemas, irrational beliefs, and negative automatic thoughts.

3. Major Diagnostic Categories of Psychological Disorders:
   • Mood Disorders (Severe emotional distortions):
     - Major Depressive Disorder (Clinical Depression): Persistent depressed mood, anhedonia (inability to experience pleasure), significant weight changes, insomnia/hypersomnia, fatigue, feelings of worthlessness, suicidal ideation.
     - Dysthymic Disorder (Persistent Depressive Disorder): Chronic, continuous, milder depressive symptoms lasting over prolonged years.
     - Bipolar Disorder (Manic-Depression): Dramatic oscillations between depression and mania (extreme euphoria, grandiosity, boundlessly energetic, racing thoughts, reckless impulsivity):
       * Bipolar I: Full-blown manic episodes causing severe social/occupational impairment.
       * Bipolar II: Milder hypomanic episodes alternating with major depression.
     - Cyclothymic Disorder: Chronic, mild cyclical mood swings between hypomania and mild depression.
   • Anxiety Disorders (Excessive, irrational, uncontrollable fear):
     - Panic Disorder: Recurrent unpredictable panic attacks (surges of intense terror, palpitations, dyspnea, trembling, dizziness, terror of impending death).
     - Agoraphobia ("Fear of the marketplace"): Intense fear and avoidance of public spaces or crowded areas where escape is difficult or help unavailable.
     - Specific Phobia: Persistent, irrational fear of specific objects or situations (heights, snakes, flying, injections).
     - Social Phobia (Social Anxiety Disorder): Marked fear of social scrutiny, evaluation, or public humiliation.
     - Obsessive-Compulsive Disorder (OCD):
       * Obsessions: Recurrent, uncontrollable intrusive thoughts causing distress.
       * Compulsions: Repetitive ritualistic behaviors (hand-washing, checking, counting) executed to neutralize anxiety.
     - Post-Traumatic Stress Disorder (PTSD): Triggered by exposure to traumatic life-threatening events (war, assault, disasters); causes flashbacks, nightmares, trauma cue avoidance, and hyperarousal.
     - Generalized Anxiety Disorder (GAD): Chronic, excessive, uncontrollable worry spanning multiple life areas (health, money, family) without a single focal trigger.
   • Personality Disorders (Enduring, rigid, maladaptive patterns):
     - Paranoid: Unjustified chronic distrust and suspicion; perceives benign acts as hostile.
     - Schizoid: Social detachment, emotional coldness, preference for solitary isolation.
     - Schizotypal: Interpersonal discomfort, eccentric behavior, magical/odd thinking.
     - Antisocial: Disregard for and violation of the rights of others; deceitful, aggressive, reckless, lacking empathy or remorse.
     - Borderline: Extreme instability in relationships, self-image, and moods; fear of abandonment, impulsivity.
     - Histrionic: Excessive, theatrical emotionality and chronic attention-seeking.
     - Narcissistic: Pervasive grandiosity, need for admiration, inflated self-importance, lack of empathy.
     - Avoidant: Pervasive social inhibition, feelings of inadequacy, hypersensitivity to rejection.
     - Obsessive-Compulsive Personality Disorder (OCPD): Rigid preoccupation with perfectionism, control, and rules at the cost of efficiency.

4. Treatment Techniques & Psychotherapy:
   • Psychotherapy: Professional psychological intervention to resolve cognitive, emotional, and behavioral problems.
   • Core Therapeutic Elements: Empathy, unconditional non-judgmental acceptance, mutual trust, and collaborative problem-solving.
   • Major Theoretical Approaches:
     - Cognitive Therapy (Beck): Identifies, challenges, and reframes automatic irrational cognitions and depressive schemas. Highly effective for mood disorders.
     - Behavioral Therapy: Deploys conditioning principles (systematic desensitization, exposure therapy, operant reinforcement, shaping) to extinguish maladaptive actions. Highly effective for phobias and OCD.
     - Psychodynamic Therapy: Uncovers unconscious conflicts, repressed childhood trauma, and defensive dynamics to build conscious insight.
     - Eclectic / Integrative Approach: Modern clinicians blend techniques from multiple schools customized to client needs.
   • Treatment Modalities:
     - Individual Therapy: One-on-one tailored private sessions.
     - Group Therapy: 3 to 20 individuals sharing common challenges; reduces isolation, provides social modeling, and fosters mutual hope.
     - Couples & Family Therapy: Resolves systemic relationship communication and structural friction.
     - Multimodal: Combining psychotherapy with biological pharmacotherapy (antidepressants, anxiolytics, mood stabilizers).

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • The 3 Diagnostic Pillars: Abnormality (statistical/cultural deviance), Maladaptiveness (functional impairment), and Personal Distress (subjective discomfort).
   • Obsession vs. Compulsion: Obsession = recurrent intrusive THOUGHT; Compulsion = repetitive ritualistic BEHAVIOR performed to reduce anxiety.
   • Bipolar I vs. Bipolar II: Bipolar I has full MANIC episodes; Bipolar II has HYPOMANIC episodes paired with major depression.
   • Agoraphobia: Fear of places where escape is difficult during panic (NOT just fear of crowds).
   • Dopamine Hypothesis: Excess dopamine activity is strongly associated with Schizophrenia; Serotonin deficit with Depression.
   • Behavioral vs. Cognitive Therapy: Behavioral targets overt actions (exposure for phobias); Cognitive targets internal schemas (reframing thoughts for depression).
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 96
    ),
    ShortNote(
        id = "psyc-u8",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 8",
        title = "Introduction to Life Skills & Psychosocial Competence",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Life skills represent psychosocial abilities that enable individuals to adapt, navigate everyday developmental challenges, maintain positive mental health, and achieve personal and professional competence. Moving from theoretical foundations to practical self-management, life skills translate psychological knowledge into actionable behavioral competencies. The World Health Organization (WHO) defines life skills as the "abilities for adaptive and positive behavior that enable individuals to deal effectively with the demands and challenges of everyday life," while UNICEF frames them as a behavioral development model balancing Knowledge, Attitudes, and Skills (KAS).

🔍 DETAILED BREAKDOWN
1. Nature, Institutional Definitions & Value of Life Skills:
   • Conceptual Overview: Psychosocial and cognitive competencies promoting psychological resilience, positive self-efficacy, and constructive interpersonal functioning.
   • WHO Definition: "Abilities for adaptive and positive behavior that enable individuals to deal effectively with the demands and challenges of everyday life."
     - Adaptive: Flexibility in adjusting approaches across diverse, shifting life circumstances.
     - Positive: Constructive, forward-looking orientation even under stress or difficulty.
   • UNICEF Definition: A behavioral change and developmental approach balancing three essential domains:
     - Knowledge (cognitive understanding).
     - Attitudes (values, beliefs, mindsets).
     - Skills (practical application and behavioral execution).
   • Value for University Students: Complements formal academic credentials with psychosocial competence, enhancing academic performance, employability, and leadership readiness.

2. Tripartite Classification of Life Skills:
   • Thinking (Cognitive) Skills:
     - Critical Thinking: Analyzing objective evidence, evaluating arguments, identifying logical fallacies, and making reasoned judgments.
     - Creative Thinking: Generating novel, innovative solutions and looking beyond conventional boundaries.
     - Decision-Making: Assessing alternatives, weighing pros and cons, predicting consequences, and choosing the optimal constructive path.
     - Problem-Solving: Recognizing discrepancies between current conditions and desired goals, formulating action plans, and overcoming obstacles.
   • Intrapersonal (Personal) Skills:
     - Self-Awareness: Accurate perception of one's own strengths, limitations, emotions, core values, motivations, and behavioral triggers.
     - Self-Esteem: Subjective emotional appraisal of one's personal self-worth, dignity, and self-respect.
     - Self-Confidence: Unshakable trust in one's abilities, judgment, and capacity to handle daily responsibilities successfully.
     - Self-Control / Emotional Regulation: Managing impulses, delaying gratification, and regulating emotional distress.
   • Interpersonal (Social) Skills:
     - Interpersonal Relationships: Building and sustaining healthy, supportive, mutually respectful human connections.
     - Reflective Communication: Active, mindful listening and self-reflective verbal and non-verbal expression.
     - Peer Pressure Resistance (Refusal Skills): Assertiveness and communication strategies to resist coercive, negative social pressure without violence or submission.
     - Empathy & Teamwork: Understanding others' feelings and collaborating toward shared objectives.
     - Conflict Resolution: Constructively resolving interpersonal disputes through negotiation, mediation, and active listening.
     - Knowing Rights & Duties: Understanding civic, legal, and moral rights alongside personal societal obligations.

3. Goals, Applications & Concrete Outcomes:
   • Primary Goals:
     - Smooth Functioning: Fostering harmonious living across household, university, social, and workplace environments.
     - Lifelong Process: Continual, dynamic upgrading of coping strategies across life transitions.
     - Rational Guidance: Objective analysis of situations to prevent impulsive or destructive choices.
   • Concrete Application Domains:
     - Health Promotion: Preventing substance abuse, drug addiction, and risky alcohol consumption.
     - Reproductive Health: Protecting against high-risk sexual behaviors, teenage pregnancy, and STIs/HIV.
     - Mental Health Protection: Preventing suicide, self-harm, depression, and interpersonal violence.
     - Broader Social Impact: Environmental stewardship, consumer awareness, community peace-building, and ethical citizenship.
   • Documented Behavioral Outcomes:
     - Significant reduction in aggressive, delinquent, and self-destructive behaviors.
     - Substantial increase in prosocial conduct, empathy, and collaborative teamwork.
     - Enhanced emotional self-regulation, impulse control, and stress management.
     - Superior academic performance, active classroom engagement, and lower dropout rates.

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • WHO Definition: Focus on the key terms "adaptive and positive behavior" to deal effectively with "demands and challenges of everyday life."
   • UNICEF Model: The KAS triad — Knowledge, Attitudes, and Skills.
   • Self-Awareness vs. Self-Esteem vs. Self-Confidence:
     - Self-Awareness = Knowing who you are (strengths, weaknesses, emotions).
     - Self-Esteem = Value/Worth placed on yourself (feeling worthy).
     - Self-Confidence = Belief in your capacity to perform and succeed.
   • Three Life Skill Categories: Thinking/Cognitive, Intrapersonal (internal self), and Interpersonal (social relationships).
   • Peer Pressure Resistance: Involves assertiveness (clear, direct, firm communication) without passive compliance or aggressive hostility.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 88
    ),
    ShortNote(
        id = "psyc-u9",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 9",
        title = "Intra-Personal and Interpersonal Skills",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Intrapersonal and interpersonal skills form the psychosocial cornerstone of emotional maturity, effective communication, and adaptive problem-solving. Intrapersonal skills encompass an individual's internal cognitive and emotional self-regulation (self-concept, self-awareness, self-esteem, self-control, and emotional intelligence), while interpersonal skills govern outward relational competence, empathetic collaboration, conflict resolution, and stress coping. Together with critical and creative thinking, these competencies translate psychological understanding into lifelong personal resilience and relational success.

🔍 DETAILED BREAKDOWN
1. Self-Concept & Self-Awareness:
   • Self-Concept:
     - Definition: A reflexive mental construct developed through social interaction and language; the totality of beliefs, perceptions, and attitudes an individual holds regarding their physical, social, academic, and moral identity.
     - Multi-dimensional: Encompasses past recollections, present evaluations, and idealized/feared future selves across diverse life domains.
   • Self-Awareness:
     - Definition: Accurate perception of one's personality, including traits, limitations, strengths, emotions, values, motivations, and thinking patterns.
     - Predictor of Success: Enables individuals to select environments aligned with their core strengths.
     - Barriers: Ego defensiveness, rationalization, and externalizing blame.
     - Cultivation: Mindfulness, seeking objective feedback, active listening, and honest self-reflection.

2. Self-Esteem & Self-Confidence:
   • Self-Esteem (Latin "aestimare" = to value/appraise):
     - Cognitive and affective self-evaluation of personal worth, dignity, and significance.
     - Competency-Based Self-Esteem: Grounded in personal efficacy, skill mastery, performance accomplishments, and social comparison.
     - Virtue-Based Self-Esteem (Self-Worth): Grounded in adherence to ethical norms, moral integrity, reciprocity, and reflected appraisals from valued others.
   • Self-Confidence (Latin "fidere" = to trust):
     - Trust in one's aptitudes, judgment, and capacity to handle everyday demands successfully.
     - Reciprocal Nature: Performance accomplishment builds confidence, which in turn elevates performance (often domain-specific).
     - Confidence vs. Courage:
       * Confidence operates within the realm of the known and familiar.
       * Courage operates within the realm of the unknown, uncertain, and fearful (requires greater moral and psychological fortitude).

3. Self-Control & The Ego Depletion Model:
   • Self-Control: The volitional capacity to inhibit immediate impulses, delay gratification, and persist through uncomfortable tasks to attain long-term goals.
   • Ego Depletion Model: Self-regulation operates like a finite mental muscle; exerting willpower on trivial tasks depletes available mental energy for high-priority challenges.

4. Anger Management & Emotional Intelligence (EI):
   • Nature of Anger: Emotional response triggered by blocked goals, perceived injustice, loss of valued possessions, or threats to self-esteem.
   • Management Strategies: Treat anger as a warning signal; defer crucial decisions while angry (anger narrows cognitive focus); understand situational complexity; view conflict from the other's perspective; check physiological states (hunger/fatigue).
   • Emotional Intelligence (EI / EQ):
     - Definition: The capacity to identify, assess, harness, and regulate emotions in oneself and others.
     - Core Triad: Emotional Awareness (labeling feelings) -> Harnessing Emotions (applying emotional energy to problem-solving) -> Emotion Regulation (soothing distress).
     - Five Competence Domains:
       * Personal Competencies: Self-awareness, self-regulation, and self-motivation.
       * Social Competencies: Social awareness (empathy) and relationship management (social skills).

5. Stress, Coping Mechanisms & Resilience:
   • Stress: Both the cognitive appraisal of threat/pressure and the physiological autonomic arousal across bodily systems.
   • Three Stressor Types: Catastrophes (unpredictable, massive disasters), Significant Life Transitions (bereavement, job loss, divorce), and Daily Hassles (micro-stressors that accumulate over time).
   • Coping Strategies (Lazarus & Folkman):
     - Problem-Focused Coping: Taking direct, constructive action to modify or remove controllable stressors (e.g., studying harder, addressing conflicts directly).
     - Emotion-Focused Coping: Regulating internal emotional distress when stressors are uncontrollable (e.g., cognitive reframing, seeking emotional support).
   • Resilience: The psychosocial capacity to adapt positively and "bounce back" from acute adversity, trauma, or chronic difficulty.

6. Critical vs. Creative Thinking:
   • Critical Thinking: Purposeful, analytical, vertical, and linear thinking using logic, hypothesis testing, and rigorous evidence evaluation (Convergent thinking; orientation toward probability; approach: "Yes, but...").
   • Creative Thinking: Generative, lateral, and imaginative thinking forming novel associations and unconventional perspectives (Divergent thinking; orientation toward possibility; suspended judgment; approach: "Yes, and...").

7. Problem Solving & Decision-Making:
   • Problem Solving: Bridging the gap between a present state and a target goal.
     - Well-Defined Problems: Explicit goals, clear steps, and structured parameters (e.g., math problems).
     - Ill-Defined Problems: Ambiguous paths, unclear definitions, and multiple possible solutions (e.g., career or relationship choices).
     - Seven Problem-Solving Steps: (1) Identify Problem -> (2) Define/Represent -> (3) Formulate Strategy -> (4) Organize Knowledge -> (5) Allocate Effort -> (6) Monitor Progress -> (7) Evaluate Solution.
   • Decision-Making: Evaluating alternatives and committing to a specific course of action.

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • Critical vs. Creative Thinking:
     - Critical = Convergent, analytic, linear, evaluative ("Yes, but...").
     - Creative = Divergent, lateral, generative, possibility-seeking ("Yes, and...").
   • Problem-Focused vs. Emotion-Focused Coping: Use problem-focused coping for CONTROLLABLE stressors; use emotion-focused coping for UNCONTROLLABLE stressors.
   • Confidence vs. Courage: Confidence acts within the familiar/known; Courage acts despite fear in the unknown.
   • Ego Depletion: Willpower is a limited, exhaustible cognitive resource.
   • Competency vs. Virtue Self-Esteem: Competency is achievement-based; Virtue is moral/ethical-based.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 91
    ),
    ShortNote(
        id = "psyc-u10",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 10",
        title = "Academic Skills, Time Management & Exam Mastery",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Academic skills encompass the cognitive strategies, self-regulation habits, and behavioral methodologies essential for scholastic success, lifelong learning, and professional competence. Anchored in psychological principles of memory, motivation, and executive functioning, this domain integrates effective time management, structured note-taking systems (e.g., Cornell and Outlining methods), evidence-based test-taking strategies (such as the SQ3R study framework), cognitive restructuring for test anxiety, SMART goal setting, and lifelong career management skills (CMS).

🔍 DETAILED BREAKDOWN
1. Time Management & Core Strategies:
   • Definition: The self-regulatory ability to plan, allocate, and intentionally control hours in a day to achieve goals effectively across academic, social, personal, and leisure domains.
   • Nature of Time: A finite, non-renewable asset; lost time cannot be regained. Requires deliberate allocation similar to financial budgeting.
   • Documented Benefits: Elevated productivity and scholastic efficiency, enhanced motivation, minimized stress, and improved self-esteem.
   • Ten Core Time-Management Strategies (Chapman & Burpured):
     1. Track Time: Audit daily activities to identify hidden leaks.
     2. Set Priorities: Distinguish urgent vs. essential long-term tasks (Eisenhower Matrix).
     3. Use Planning Tools: Utilize physical agendas, task checklists, or digital calendars.
     4. Get Organized: Keep study materials systematically arranged to reduce retrieval friction.
     5. Schedule Deep Blocks: Reserve dedicated, uninterrupted blocks for intensive cognitive study.
     6. Delegate: Share or distribute collaborative duties when working in peer teams.
     7. Eliminate Procrastination: Initiate academic tasks immediately rather than postponing.
     8. Manage Distractions: Regulate notifications, unplanned visits, and environmental noise.
     9. Avoid Multitasking: Practice single-tasking to preserve working memory bandwidth.
     10. Maintain Physical Wellness: Ensure adequate sleep, balanced nutrition, and regular exercise.

2. Note-Taking & Active Study Methodologies:
   • Function: Facilitates active cognitive encoding during lectures and provides structured external storage for review.
   • Three Stages of Effective Note-Taking:
     - Before Class (Preparation): Review prior notes and syllabus outlines; complete background readings; formulate questions on difficult concepts.
     - During Class (Active Recording): Maintain focus on central themes; paraphrase into personal vocabulary rather than transcribing verbatim; leave gaps when falling behind to maintain listening flow.
     - After Class (Consolidation): Review and edit notes immediately post-lecture; discuss ambiguities with instructors or peers; summarize concepts aloud.
   • Common Note-Taking Systems:
     - Cornell Method: Formats the page into three functional sectors:
       * Note-Taking Area (Right column): Captures raw lecture concepts during class.
       * Cue Column (Left column): Formulates review questions, key terms, and prompts after class.
       * Summary Box (Bottom): 2-to-3 sentence synthesis consolidating the page's core ideas.
     - Outlining Method: Hierarchical indentation structure (Roman numerals -> capital letters -> numbers) for structured conceptual taxonomies.
     - Charting Method: Tabular matrices organizing information across comparative rows and columns (ideal for dates, competing theories, or opposing perspectives).

3. Test-Taking Skills & The SQ3R System:
   • Systematic Preparation: Consistent lecture attendance, distributed practice across the term (avoiding massed last-minute cramming), analyzing past examination questions, and establishing incremental milestones.
   • The SQ3R Study Method:
     - S - Survey: Skim chapter headings, subheadings, diagrams, and summary blocks to build a mental scaffold.
     - Q - Question: Convert headings into inquiry questions (e.g., "What are the stages of memory?").
     - R1 - Read: Read actively to locate specific answers to formulated questions.
     - R2 - Recite: Mentally rehearse or speak answers aloud from memory without looking at text.
     - R3 - Review: Periodically review notes over spaced intervals to reinforce retrieval pathways.
   • Exam Execution: Confirm testing formats (objective vs. essay); get adequate sleep; arrive early; regulate breathing; carefully read instructions before answering.

4. Test Anxiety & Cognitive Restructuring:
   • Nature: An unpleasant affective mood state marked by physiological tension, apprehension, and catastrophic cognitive worry regarding impending examinations. Excessive adrenaline disrupts working memory and impairs recall.
   • Multidimensional Symptoms:
     - Physical: Tachycardia, tremors, nausea, dyspnea, headaches, diaphoresis (sweating).
     - Emotional: Dread, helplessness, irritability, panicky feelings.
     - Behavioral: Fidgeting, restlessness, procrastination, task avoidance.
     - Cognitive: "Going blank", intrusive negative self-talk, attentional disorganization.
   • Overcoming Anxiety via Cognitive Restructuring (3 Steps):
     1. Pay Attention to Internal Self-Talk: Identify automatic catastrophic thoughts (e.g., "I'm going to fail and ruin my future").
     2. Identify Thinking Traps: Pinpoint underlying cognitive distortions (black-and-white thinking, catastrophizing, fortune-telling).
     3. Challenge & Reframe: Evaluate evidence objectively and substitute balanced, realistic appraisals (e.g., "I have studied consistently and reviewed past questions; I am prepared to handle this exam").

5. SMART Goal Setting & Career Development:
   • Goal Setting: The deliberate process of imagining, specifying, and executing actions toward an intended future outcome.
   • SMART Goal Criteria:
     - S: Specific (clearly defined targets).
     - M: Measurable (quantifiable criteria for progress).
     - A: Action-oriented / Achievable (realistic action steps).
     - R: Realistic / Relevant (aligned with capabilities and values).
     - T: Time-bound (clear target deadlines).
   • Functions: Focuses selective attention, mobilizes persistent effort, establishes performance standards, and enhances self-efficacy.
   • Career Development & CMS:
     - Career: An evolving sequence of life, learning, and occupational roles across the lifespan.
     - Career Development: Continuous self-discovery regarding abilities, interests, values, and environmental workplace realities.
     - Career Management Skills (CMS): Proactive competencies enabling individuals to self-assess skills, navigate occupational pathways, and intentionally pursue personal and professional aspirations.

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • Cornell System: 3 areas (Notes column right, Cue column left, Summary bottom).
   • SQ3R Sequence: Survey -> Question -> Read -> Recite -> Review.
   • SMART Criteria: Specific, Measurable, Action-oriented, Realistic, Time-bound.
   • Cognitive Restructuring for Test Anxiety: Pay attention to self-talk -> Identify anxious thoughts -> Challenge and reframe thinking traps.
   • Time Management: Proactive single-tasking and prioritizing deep study blocks out-perform reactive multi-tasking.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 86
    ),
    ShortNote(
        id = "psyc-u11",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 11",
        title = "Social Skills, Diversity & Conflict Resolution",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Social skills encompass the psychosocial and communicative competencies required to interact effectively, build positive relationships, navigate cultural diversity, and resolve interpersonal conflicts constructively. In an interconnected society, psychological competence extends beyond intrapersonal self-regulation to social fluency: managing diversity, advancing gender inclusion, practicing open assertiveness, resisting coercive peer pressure, resolving disputes through collaborative "Win-Win" problem-solving, fostering high-performing team dynamics, and mitigating risky adolescent behaviors.

🔍 DETAILED BREAKDOWN
1. Cultural Diversity & Diversity Management:
   • Cultural Diversity: Human social environments are multifaceted, with identity shaped by overlapping dimensions: ethnicity, nationality, religion, gender, language, socio-economic status, and politics.
   • Ethnocentrism vs. Multiculturalism:
     - Culture as Evaluation Standard: Judging other cultural practices exclusively through the lens of one's own culture creates prejudice, fear of the unfamiliar, and discrimination. Exposure to radical cultural differences can provoke Culture Shock.
     - Multiculturalism: A framework that actively recognizes, respects, and values cultural differences, fostering an inclusive climate where all diverse groups contribute equally.
   • Core Principles of Diversity Management:
     - Recognize the human capacity for complex, multifaceted social identities.
     - Proactively engage across cultural boundaries to build intercultural empathy.
     - Avoid imposing personal assumptions, values, or stereotypes onto others.
     - Linguistic Empathy: Second-language limitations reflect linguistic exposure, never innate intellectual capacity.
     - Intervene constructively against micro-aggressions, cultural insensitivity, or discrimination.

2. Gender & Social Inclusion:
   • Sex vs. Gender:
     - Sex: Innate biological, anatomical, chromosomal, and physiological distinctions.
     - Gender: Socially constructed roles, cultural institutions, and behavioral expectations assigned to males and females.
   • Barbara Risman's Three Levels of Gender Analysis:
     1. Individual Level: Early gender socialization and internal identity development across the lifespan.
     2. Interactional Level: Interpersonal expectations, cultural stereotypes, and social pressure regarding how men and women "should" act.
     3. Structural Level: Macro-institutional arrangements, wage disparities, legal policies, and unequal distribution of resources.
   • Promoting Gender Equity: Discarding discriminatory socioeconomic barriers, ensuring equitable educational access, and securing equal leadership representation.

3. Interpersonal Communication & Social Influence:
   • Interpersonal Communication: Dynamic exchange of information, ideas, and emotional states through verbal (diction, syntax) and non-verbal channels.
   • Non-Verbal Communication: Facial expressions, eye contact (gaze), body posture, physical distance (proxemics), and paralinguistics (tone, volume, speech rate).
   • Social Influence: When an influencing agent uses persuasion, authority, promises, or threats to alter a target individual's behavior.
     - Attitude Change: Genuine, deep internal transformation of beliefs, perspectives, and values.
     - Compliance: Outward behavioural agreement to a request or demand without genuine internal agreement.

4. Peer Pressure & Resistance Strategies:
   • Peer Pressure: Social force exerted by a peer network to adopt their norms, values, or actions.
     - Positive: Motivates academic achievement, prosocial volunteerism, healthy lifestyle choices, and sportsmanship.
     - Negative: Uses manipulation, teasing, or coercion to push individuals toward risky, illegal, or harmful actions.
   • Assertive Refusal & Resistance Strategies:
     - Recognize coercive or belittling manipulation immediately.
     - Use the "Delay Tactic": Pausing to reflect before reacting impulsively.
     - Deliver clear, direct, and unapologetic refusals ("No thanks, that doesn't work for me").
     - Cultivate relationships with peers who respect autonomy and personal boundaries.
     - Bystander Intervention: Actively supporting fellow peers facing social intimidation.

5. Assertiveness vs. Passivity vs. Aggressiveness:
   • Assertiveness: Expressing feelings, beliefs, needs, and boundaries directly, honestly, and calmly while preserving mutual respect for others' dignity.
   • Passivity: Concealing authentic feelings, appeasing others, and relinquishing rights; breeds low self-esteem, unexpressed resentment, and helplessness.
   • Aggressiveness: Forcing one's will onto others through intimidation, verbal hostility, or coercion; disregards others' rights.
   • Core Assertive Skills: Clear "I" statements, maintaining composed eye contact, active listening, and admitting errors without defensiveness.

6. Conflict & Constructive Resolution:
   • Nature of Conflict: Incompatibility of goals, values, or scarce resources between interdependent parties; inevitable in human interaction.
   • Daniel Katz's Four Sources of Conflict:
     1. Economic Conflict: Competition over scarce material or financial resources.
     2. Value Conflict: Incompatibility between worldviews, moral principles, or religious ideologies.
     3. Power Conflict: Struggles to gain, maintain, or maximize authority, control, or status.
     4. Communication Breakdowns: Misperceptions, rumors, and cognitive distortions creating conflict without genuine material incompatibility.
   • Five Levels of Conflict: Intrapersonal -> Interpersonal -> Role Conflict -> Intergroup/Societal -> International.
   • Conflict Resolution Approaches:
     - Win–Lose (Competitive): One party dominates at the other's expense; breeds resentment and unstable compliance.
     - Lose–Lose (Compromise/Sacrifice): Both parties absorb losses or settle for sub-optimal outcomes.
     - Win–Win (Collaborative Integrative): Reframing the dispute into "We versus the problem"; maximizes mutual gains and strengthens long-term partnership.

7. Teamwork & Overcoming Risky Behaviors:
   • Teamwork: Group with complementary skills mutually committed to a shared mission, common performance goals, and joint accountability.
   • High-Performance Team Traits: Transparent communication, psychological safety, equitable workload distribution, mutual trust, and facilitative leadership.
   • Mitigating Adolescent Risky Behaviors: Strengthening family stability, deepening student-school-teacher bonds, expanding youth community clubs, and fostering mentorship with trusted adults.

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • Sex vs. Gender: Sex is biological/physiological; Gender is socially and culturally constructed.
   • Risman's 3 Gender Levels: Individual (socialization), Interactional (interpersonal norms), Structural (institutional policies).
   • Conflict Resolution: Win-Win is the collaborative model ("we vs. the problem"), contrasting with adversarial Win-Lose.
   • Katz's Conflict Typology: Economic (scarce goods), Value (ideology/morals), Power (authority/dominance), Communication (misunderstanding).
   • Assertiveness Formula: Direct, honest expression of needs while respecting others' rights (contrasts with passive submission or aggressive dominance).
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 90
    ),

    // =========================================================================
    // 4. LOGIC & CRITICAL THINKING (c4, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "logic-u1",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 1",
        title = "Introducing Philosophy: Nature, Features, Fields & Value",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Philosophy originates from the ancient Greek words "Philo" (love) and "Sophia" (wisdom) — literally translating to the "love of wisdom." Coined by Pythagoras and championed by Socrates ("The unexamined life is not worth living"), philosophy is an active, open-ended, and critical enterprise without a bounded empirical subject matter. It operates through both a Constructive Dimension (formulating coherent, rationally defensible frameworks regarding reality, knowledge, and value) and a Critical Dimension (rigorous analysis, conceptual clarification, and evaluation of competing claims).

🔍 DETAILED BREAKDOWN
1. Meaning, Etymology & Nature of Philosophy:
   • Etymological Roots: Greek "Philo" (love) + "Sophia" (wisdom) = "Love of wisdom." Pythagoras was the first to call himself a philosopher (lover of wisdom).
   • The Elusive Nature: Unlike specialized sciences (physics, chemistry) that inspect bounded material domains, philosophy possesses no single restricted subject matter; it examines universal foundational questions.
   • Socratic Insight: "Wonder is the feeling of a philosopher, and philosophy begins in wonder." Genuine philosophy requires moving beyond passive wonder into systematic, critical reflection.
   • Wisdom vs. Technical Expertise: Technical expertise is factual, specialized data recall; philosophical wisdom is a critical habit of mind, intellectual vigilance, and an unceasing commitment to pursue truth wherever it leads.
   • "Questioning the Apparent": Peeling back common-sense assumptions and unexamined dogmas (Vincent Barry: "an active imaginative process of formulating proper questions and resolving them by rigorous, persistent analysis").
   • Dual Interdependent Dimensions:
     - Constructive Dimension: Developing rationally defensible, systematic answers to fundamental questions.
     - Critical Dimension: Rational critique, deconstruction, and evaluation of existing assumptions.
   • Philosophy as Dynamic Activity: Philosophy is a living process ("philosophizing") rather than a static catalog of memorized doctrines.

2. Five Basic Features of Philosophy:
   • 1. Informal vs. Formal Sense:
     - Informal ("Having" a philosophy): An unreflective personal attitude, passive outlook, or general worldview.
     - Formal ("Doing" philosophy): The active, disciplined, and self-conscious critical examination of fundamental beliefs.
   • 2. A Reflective and Critical Enterprise: Why philosophers disagree:
     - Diverse cultural backgrounds, historical epochs, and training.
     - A dynamic, constantly evolving natural and social world.
     - Incomplete empirical evidence for ultimate metaphysical and normative questions.
   • 3. A Holistic, Comprehensive Worldview: Synthesizes fragmented findings of natural sciences, historical insights, and human experiences into a unified, coherent synthesis.
   • 4. Logical Analysis of Language & Conceptual Clarification: Dissolves linguistic confusion, exposes nonsense, and ensures precision in thought and communication.
   • 5. Inquiry into Perennial Human Questions: Enduring questions spanning existence, justice, free will, morality, and truth that transcend empirical factual queries.

3. Core Fields of Philosophy — Metaphysics & Epistemology:
   • Metaphysics (Study of Ultimate Reality and Being):
     - Etymology: Greek "meta" (beyond/after) + "physika" (physics) — coined by Andronicus of Rhodes when cataloging Aristotle's works.
     - George Knight's Floor Example: Common sense sees a solid stationary floor; the physicist sees swirling subatomic particles and energy; the chemist sees hydrocarbon polymers. Reality is multilayered!
     - Four Core Subsets:
       1. Cosmological Aspect: Theories regarding the origin, evolution, and orderly structure of the universe (purpose vs. random chance).
       2. Theological Aspect: Religious conceptions, proofs of God's existence, divine attributes, and the Problem of Evil.
       3. Anthropological Aspect: Philosophical inquiry into human nature (mind-body problem, free will vs. determinism, moral nature, immortality).
       4. Ontological Aspect: Study of Being and existence as such (materialism vs. idealism, static vs. dynamic reality).
   • Epistemology (Study of Knowledge):
     - Etymology: Greek "episteme" (knowledge) + "logos" (study of).
     - Foundational Positions:
       * Skepticism: Asserts objective knowledge is unattainable (e.g., Gorgias the Sophist).
       * Agnosticism: Profession of ignorance regarding transcendent or ultimate reality.
       * Relativism: Truth is culture-bound and shifting vs. Absolutism: Truth is eternal, universal, and objective.
     - Five Sources of Knowledge:
       1. Empiricism: Sensory perception (sight, hearing, touch). Strength: Publicly verifiable and replicable. Limitation: Senses can deceive (e.g., bent stick in water).
       2. Rationalism: Pure reason, deduction, and abstract logic. Strength: Internal certainty (e.g., mathematics). Limitation: Can detach from empirical facts.
       3. Intuition: Immediate insight below conscious awareness. Strength: Sparks creative breakthroughs. Limitation: Highly subjective and prone to error.
       4. Revelation: Transcendent divine disclosure. Strength: Perceived as omniscient/pure by believers. Limitation: Relies on faith; non-verifiable empirically.
       5. Authority: Established expertise, textbooks, and traditions. Strength: Preserves cultural heritage. Limitation: Perpetuates dogmatic error if source is biased.

4. Core Fields of Philosophy — Axiology & Logic:
   • Axiology (Study of Value):
     - Etymology: Greek "axios" (worth/value) + "logos".
     - Three Branches:
       1. Ethics (Moral Philosophy): Right vs. wrong conduct.
          * Normative Ethics: Consequentialist/Teleological (ends justify means), Deontological (duty-based rules), Virtue Ethics (moral character).
          * Meta-Ethics: Meaning of ethical words (good, bad) and objectivity of moral facts.
          * Applied Ethics: Concrete dilemmas (euthanasia, abortion, environmental ethics).
       2. Aesthetics: Nature of beauty, art, taste, and sensory evaluation (objective qualities vs. subjective viewer appraisal).
       3. Social & Political Philosophy: Justification of governance, legitimacy of political power, justice, and civic obligation.
   • Logic: The study of arguments and the methods used to distinguish valid, sound reasoning from fallacious reasoning.

5. Significance of Studying Philosophy:
   • Socratic Dictum: "The unexamined life is not worth living." Fulfills actualizing needs beyond mere survival maintenance.
   • Five Core Benefits:
     1. Intellectual and Behavioral Independence (emancipation from unexamined societal prejudices).
     2. Deep Reflective Self-Awareness.
     3. Intellectual Humility, Tolerance, and Open-Mindedness.
     4. Rigorous Creative and Critical Thinking.
     5. Constructing a Feasible, Purposeful Value Framework.
   • Bertrand Russell on Philosophical Uncertainty (1912, "The Problems of Philosophy"):
     - Unphilosophical living leaves one imprisoned by cultural prejudices and habitual dogmas.
     - Philosophy's value lies in its liberating uncertainty: while diminishing dogmatic arrogance about what things are, it dramatically expands our knowledge of what they may be, keeping intellectual wonder alive.

💡 EXAM TIPS & HIGH-YIELD FOCUS
   • Etymological Origins: Philo + Sophia (Love of wisdom), Episteme + Logos (Knowledge), Axios + Logos (Value), Meta + Physika (Beyond physics).
   • Socrates: "Philosophy begins in wonder" and "The unexamined life is not worth living."
   • Pythagoras: First thinker to formally coin and use the term "philosopher."
   • Four Metaphysical Branches: Cosmology (universe), Theology (God), Anthropology (human nature), Ontology (being/existence).
   • Consequentialist vs. Deontological Ethics: Consequentialism judges morality by OUTCOMES; Deontology judges morality by DUTY/RULES regardless of consequences.
   • Russell's Value of Philosophy: Resides in its liberating uncertainty, not in providing definitive scientific answers.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 85
    ),
    ShortNote(
        id = "logic-u2",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 2",
        title = "Basic Concepts of Logic: Arguments, Deduction, Induction & Evaluation",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Logic is the philosophical science and systematic study of methods and principles used to evaluate arguments—distinguishing correct (good) from incorrect (bad) reasoning. An argument is a structured group of statements where premises provide evidentiary support or reasons to establish a single conclusion.

🔍 DETAILED BREAKDOWN
1. What is Logic?
   • Etymology: Originates from the ancient Greek "logos" (word, sentence, discourse, reason, rule, or truth).
   • Core Definitions:
     - The organized body of knowledge or philosophical science that evaluates arguments.
     - The systematic study of methods and principles distinguishing good reasoning from bad reasoning.
     - An effort to codify formal rules of rational thought to determine whether premises adequately support conclusions.
   • Benefits of Studying Logic:
     - Refines natural reasoning, critical thinking, and argumentative skills.
     - Equips individuals to construct sound arguments and critically assess others' claims.
     - Defends against prejudice, unreflective dogmatism, and misleading rhetoric in public and personal life.
     - Detects errors in reasoning and semantic confusions from language misuse.

2. What is an Argument?
   • Technical Definition: A group of statements in which one or more statements (premises) are claimed to provide support, reasons, or evidence to believe another single statement (conclusion).
   • Non-Technical Contrast: Not a mere dispute, quarrel, or verbal fight between individuals.
   • Statements and Truth Values:
     - Statement (Proposition): A declarative sentence having a truth value (either strictly true or false).
     - Truth: Describes things as they actually are in reality.
     - Falsity: Asserts what is not the case.
   • Non-Statements (Cannot serve as premises or conclusions):
     - Questions ("Would you close the window?")
     - Proposals ("Let us study together")
     - Commands / Imperatives ("Give me your ID card!")
     - Suggestions ("I suggest reading philosophy")
     - Exclamations ("Right on!")
   • Components of an Argument:
     - Premise(s): Statements setting forth evidence, reasons, or grounds. Must contain at least one premise (can contain many).
     - Conclusion: The single statement claimed to follow from the premises; the central claim established. Every argument has one and only one conclusion.
     - Canonical Model:
       * Premise 1: All Ethiopians are Africans.
       * Premise 2: Tsionawit is an Ethiopian.
       * Conclusion: Therefore, Tsionawit is an African.

3. Identifying Premises and Conclusions:
   • Conclusion Indicators:
     - Therefore, thus, so, consequently, hence, whence, wherefore, accordingly, it follows that, we may conclude, entails that, as a result, it implies that, it shows that.
   • Premise Indicators:
     - Since, because, for, as, given that, seeing that, owing to, in that, inasmuch as, as indicated by, for the reason that, may be inferred from.
   • Crucial Distinction ("For this reason" vs "For the reason that"):
     - "For this reason": Preceding statement is the PREMISE; following statement is the CONCLUSION.
     - "For the reason that": Introduces a PREMISE.
   • Arguments Without Indicators:
     - Determine the conclusion by asking: What is the arguer trying to prove? What is the main point? Which single claim follows from the others?
     - Extraneous remarks and background comments are neither premises nor conclusions and must be excluded.

4. Techniques of Recognizing Arguments:
   • Two Essential Conditions for Purporting to Prove Something:
     1. Factual Claim: At least one statement claims to present evidence, facts, or reasons.
     2. Inferential Claim: There is a claim that the alleged evidence supports, implies, or leads to the conclusion.
        - Inferential claim is an objective structural property of language, not the subjective intent of the author.
        - Explicit: Directly signaled by indicator words ("therefore", "because").
        - Implicit: Established through an inferential relationship without indicators.
   • Non-Argumentative Passages (Lack an Inferential Claim):
     1. Simple Non-Inferential Passages:
        - Warnings: Putting someone on guard against potential danger ("Never confide secrets to strangers").
        - Pieces of Advice: Recommendations on future courses of action without logical proof.
        - Statements of Belief / Opinion: Personal convictions lacking supporting evidence.
        - Loosely Associated Statements: Share a general topic but lack inferential connection.
        - Reports: Conveying factual data/narrative without attempting to prove a conclusion.
     2. Expository Passages:
        - Begins with a topic sentence followed by sentences that merely elaborate, clarify, or flesh it out.
        - Borderline Test: If subsequent sentences merely describe, it is expository; if intended to prove an unfamiliar or contested topic sentence, it functions as an argument.
     3. Illustrations & Arguments from Example:
        - Illustrations: Examples demonstrating what something means or how it operates (e.g., chemical formulas).
        - Argument from Example: Examples explicitly cited to substantiate a contested general claim.
     4. Explanations:
        - Sheds light on or accounts for WHY an accepted fact or phenomenon occurred (does NOT prove that it occurred).
        - Explanandum: Statement describing the accepted event/fact to be explained.
        - Explanans: Statement(s) purporting to do the explaining (causes/reasons).
        - Difference: Argument seeks to prove a claim is TRUE (Premises -> Conclusion); Explanation shows WHY an accepted fact is true (Explanans -> Explanandum).
     5. Conditional Statements ("If... then..."):
        - Antecedent (follows "if") and Consequent (follows "then").
        - A single conditional statement is NEVER an argument (neither component is asserted as true).
        - Can serve as premises, conclusions, or both within an argument.
        - Sufficient Condition (A for B): Occurrence of A is all that is needed for B (If A, then B). E.g., Being a dog is a sufficient condition for being an animal.
        - Necessary Condition (B for A): A cannot occur without B (If not B, then not A). E.g., Being an animal is a necessary condition for being a dog.

5. Types of Arguments: Deduction vs. Induction:
   • Core Differences:
     - Deductive Argument: Claims it is IMPOSSIBLE for the conclusion to be false if premises are true. Involves strict, necessary reasoning. Conclusion does not go beyond the scope of premises.
     - Inductive Argument: Claims it is IMPROBABLE for the conclusion to be false if premises are true. Involves probabilistic reasoning. Conclusion goes beyond the content of premises.
   • Factors Distinguishing Deduction from Induction:
     1. Indicator Words: Deductive ("definitely", "certainly", "necessarily", "absolutely") vs Inductive ("probably", "likely", "plausible", "reasonable to conclude").
     2. Actual Strength of Inferential Link: Necessary connection (deductive) vs Probable connection (inductive).
     3. Form or Character of Argumentation.
   • Common Deductive Forms:
     - Arguments Based on Mathematics: Arithmetic or geometric deductions (excludes statistics, which are inductive).
     - Arguments from Definition: Conclusion depends entirely on definition of a word (e.g., "Angel is honest; therefore, Angel tells the truth").
     - Syllogisms (2 premises + 1 conclusion):
       * Categorical Syllogism: Begins with "all", "no", or "some".
       * Hypothetical Syllogism: Contains conditional ("if... then") premises.
       * Disjunctive Syllogism: Contains an "either... or" premise.
   • Common Inductive Forms:
     - Predictions: Reasoning from past/present observations to future occurrences.
     - Arguments from Analogy: Shared properties in two things imply sharing an additional property.
     - Inductive Generalizations: Projecting sample group traits onto an entire population.
     - Arguments from Authority: Concluding based on expert testimony or witness statements.
     - Arguments Based on Signs: Concluding a condition exists from visual markers/signs.
     - Causal Inferences: Inferring effect from cause, or cause from observed effect.
   • Debunking Historical Misconception: Deductive arguments do NOT always proceed general-to-particular, nor induction particular-to-general. Both can proceed in either direction.

6. Evaluating Arguments:
   • Two-Step Evaluation Order:
     Step 1: Evaluate Inferential Claim (Do premises logically support conclusion?).
     Step 2: Evaluate Factual Claim (Are premises actually true in reality?).
   • 6.1 Deductive Evaluation: Validity & Soundness
     - Validity (Structural Inferential Test):
       * Valid: Impossible for premises to be true and conclusion false simultaneously. Conclusion follows with strict logical necessity.
       * Invalid: Possible for premises to be true and conclusion false. Conclusion fails to follow necessarily.
       * Core Rule: Validity depends strictly on form/structure, NOT factual truth!
       * The ONLY Impossible Combination for Validity: True Premises + False Conclusion = Guaranteed INVALID.
       * Validity Matrix:
         · True Premises + True Conclusion -> Valid OR Invalid
         · True Premises + False Conclusion -> MUST BE INVALID
         · False Premises + True Conclusion -> Valid OR Invalid
         · False Premises + False Conclusion -> Valid OR Invalid
     - Soundness (Inferential + Factual Test):
       * Sound Argument = Valid Argument + All Actually True Premises (Conclusion is guaranteed to be true).
       * Unsound Argument: Any deductive argument that is invalid, contains one or more false premises, or both.
   • 6.2 Inductive Evaluation: Strength & Cogency
     - Strength (Probabilistic Inferential Test):
       * Strong: Improbable for conclusion to be false assuming premises are true (>50% probability).
       * Weak: Conclusion does not follow with probability, even if claimed to.
       * Unlike validity (all-or-nothing), inductive strength admits of degrees.
       * The ONLY Impossible Combination for Strength: True Premises + False Conclusion = Guaranteed WEAK.
       * Strength Matrix:
         · True Premises + True Conclusion -> Strong OR Weak
         · True Premises + False Conclusion -> MUST BE WEAK
         · False Premises + True Conclusion -> Strong OR Weak
         · False Premises + False Conclusion -> Strong OR Weak
     - Cogency (Inferential + Factual Test):
       * Cogent Argument = Strong Argument + All Actually True Premises + Satisfies Total Evidence Requirement (does not ignore key evidence).
       * Uncogent Argument: Inductive argument that is weak, has one or more false premises, or ignores critical countervailing evidence.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The "Golden Matrix Rule": The ONLY combination that definitively determines inferential status alone is [True Premises + False Conclusion]. In deduction it guarantees INVALIDITY; in induction it guarantees WEAKNESS. All other truth-value combinations can be either valid/invalid or strong/weak.
• Soundness & Cogency require BOTH valid/strong structure AND factually true premises. A single false premise instantly ruins soundness or cogency!
• Never confuse conditional statements or explanations with arguments. "If P then Q" asserts nothing as true; an explanation explains WHY an accepted fact occurred rather than proving THAT it occurred.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 89
    ),
    ShortNote(
        id = "logic-u3",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 3",
        title = "Logic and Language: Philosophy, Meaning, Terms & Definitions",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Language is the primary vehicle for human thought and logical reasoning. Critical thinking requires understanding the philosophy of language, separating cognitive meaning from emotive distortion, resolving vagueness and ambiguity, analyzing terms through intension and extension, and constructing rigorous definitions following logical criteria.

🔍 DETAILED BREAKDOWN
1. Philosophy of Language: An Overview
   • Definition & Scope: A reasoned investigation into the nature of meaning, origins and cognition of language, its use in thought, and the relationship connecting linguistic symbols, logic, and external reality.
   • Distinction from Linguistics:
     - Linguistics empirically studies grammar, phonetics, and comparative structural features of specific languages.
     - Modern Linguistics (Noam Chomsky): Highlights Universal Grammar—an innate cognitive foundation—distinguishing internal competence (I-languages) from external social conventions (E-languages).
   • Semantic Holism: Proposes that meaning does not reside within an isolated word or proposition, but emerges solely through the wider web of an entire linguistic system.
   • Historical Milestones:
     - Ancient Greece: Plato debated natural vs. conventional naming; Aristotle analyzed predicates and properties (laying roots for nominalism and moderate realism).
     - The Stoics: Introduced the "lektón" (intelligible sense of a statement), foreshadowing modern propositions with truth-values.
     - Scholastic Logic: Explored ambiguity, vagueness, supposition (context-dependent meaning), and categorized terms into categorematic (independent meaning) vs. syncategorematic (auxiliary grammatical markers).
     - Modern Era: The "Linguistic Turn" of the 19th and 20th centuries placed linguistic analysis at the center of analytic philosophy.
   • Philosophical Theories of Meaning:
     - Idea Theories (Locke, Berkeley, Hume): Meaning consists of subjective mental representations/ideas triggered by signs.
     - Truth-Conditional Theories (Gottlob Frege): Meaning is defined by objective conditions under which a statement is judged true or false.
     - Use Theories (Ludwig Wittgenstein): Meaning is determined by how words are applied in social practices and speech acts ("meaning is use").
     - Reference Theories / Externalism (Tyler Burge, Saul Kripke): Meaning corresponds directly to real-world objects designated by terms.
     - Verificationist Theories (Logical Positivists): Meaning is identical to its empirical method of verification or falsification.
     - Pragmatist Theories: Meaning is grounded in the practical, observable consequences of applying an idea.

2. Logic and Meaning: Cognitive vs. Emotive
   • Two Principal Functions in Logic:
     - Cognitive Function: Communicates factual information, assertions, or evidence; conveys verifiable descriptions and carries truth-values. Primary focus of logic!
     - Emotive Function: Expresses or evokes feelings, affective reactions, and emotional states.
   • Emotive Terminology in Arguments:
     - Value Claims: Judgments asserting that an action/phenomenon is good, bad, moral, immoral, better, or worse.
     - Logical Danger: Emotional vocabulary masks unsubstantiated value claims, generating persuasive momentum that nudges audiences to accept claims without evidence.
     - Remedy: Disengage emotional language, isolate underlying value claims, and restate the argument into neutral premises to evaluate validity.

3. Deficiencies of Cognitive Meaning & Types of Disputes:
   • Vagueness vs. Ambiguity:
     - Vagueness: Occurs when an expression has borderline cases, creating a continuous, hazy blur of meaning (e.g., "rich", "poor", "fresh", "excessive", "polluted"). Impossible to determine exact application boundaries.
     - Ambiguity: Occurs when an expression admits of two or more distinct, separate meanings in a single context (e.g., "bank", "light", "sound", "proper").
   • Forms of Disputes in Logic:
     - Verbal Disputes: Disagreements arising from linguistic misunderstandings, vague terminology, or ambiguous words. Resolved by clarifying definitions.
     - Factual Disputes: Genuine disagreements over empirical facts, verifiable events, or objective states of affairs.

4. The Intension and Extension of Terms:
   • Term: Any word or syntactic phrase that can serve as the subject of a declarative statement (proper names, common nouns, descriptive phrases). Standalone verbs, adverbs, prepositions, and conjunctions are NOT terms.
   • Use vs. Mention: A word is used when directing attention to reality ("Paris is crowded"); it is mentioned when set in quotes to examine the word itself ("'Paris' has five letters").
   • Intensional Meaning (Connotation / Sense):
     - The set of attributes, qualities, or characteristics implied by a term.
     - Conventional Connotation: Standard attributes recognized by competent speakers, maintaining stable meaning.
   • Extensional Meaning (Denotation / Reference):
     - The actual physical entities or class members referred to across past, present, and future.
     - Empty Extension (Null Class): Terms with clear intension but zero real-world referents (e.g., "unicorn", "current king of France").
   • Core Rule: Intension determines extension (attributes establish membership criteria).
   • Ordering of Terms:
     - Increasing Intension (Decreasing Extension): Broader category -> progressively more specific concept with more attributes (e.g., Animal -> Mammal -> Feline -> Tiger).
     - Increasing Extension (Decreasing Intension): Smaller class -> progressively larger class with more members (e.g., Tiger -> Feline -> Mammal -> Animal).

5. Logic and Definition: Types and Purposes:
   • Structure: Definition = Definiendum (word being defined) + Definiens (words performing the defining).
   • Five Types of Definitions:
     1. Stipulative Definition: Assigns meaning to a term for the first time (coining a word or giving a new meaning). Arbitrary; cannot be true or false.
     2. Lexical Definition: Reports the conventional meaning already established in standard usage (dictionary definition). Can be true or false; eliminates ambiguity.
     3. Précising Definition: Reduces the vagueness of a term by establishing explicit operational boundaries for specialized settings (legal, medical, scientific). Non-arbitrary.
     4. Theoretical Definition: Formulates a scientific or philosophical theory that conceptualizes the true nature of an entity (e.g., defining heat as kinetic molecular motion).
     5. Persuasive Definition: Employs emotionally charged vocabulary to influence audience attitudes while disguising itself as an objective definition.

6. Definitional Techniques:
   • Extensional (Denotative) Techniques (assigns meaning by pointing to or naming class members):
     - Demonstrative (Ostensive): Physically pointing at an object (limited by physical presence).
     - Enumerative: Naming individual class members individually ("Actor means someone like X, Y, or Z").
     - Definition by Subclass: Naming specific subgroups that make up the class ("Tree means oak, pine, or cedar").
     - Limitation: Extensional definitions cannot fully determine intension.
   • Intensional (Connotative) Techniques (specifies attributes and qualities):
     - Synonymous Definition: Providing a single word sharing the same intensional meaning.
     - Etymological Definition: Tracing ancestral linguistic roots to convey seminal meaning.
     - Operational Definition: Specifying measurable, observable empirical actions or experimental tests.
     - Definition by Genus and Difference: Identifies the broader category (Genus) and the distinguishing characteristic (Specific Difference) setting the target species apart.

7. Criteria for Lexical Definitions (8 Core Rules):
   1. Standard Grammar: Must conform to established syntactic conventions.
   2. Convey Essential Meaning: Must capture primary distinguishing attributes of the definiendum.
   3. Neither Too Broad nor Too Narrow:
      - Too Broad: Includes entities outside the denoted class (e.g., "A bird is a feathered creature that flies" - excludes ostriches).
      - Too Narrow: Excludes legitimate members of the class.
   4. Avoid Circularity: Must not define a term using itself, close grammatical derivatives, or a circular chain.
   5. Affirmative over Negative: State what a term IS rather than what it is NOT (unless defining inherently negative terms like "darkness" or "bald").
   6. Avoid Figurative, Obscure, Vague, or Ambiguous Language: Avoid metaphors and confusing jargon.
   7. Avoid Affective Terminology: Refrain from biased, sarcastic, or emotionally manipulative phrasing.
   8. Indicate Context: Specify specialized field/discipline when a term carries multiple technical senses.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Vagueness = blurred boundaries / borderline cases ("poor", "tall"). Ambiguity = multiple distinct meanings ("bank", "light").
• Intension determines extension, but not vice versa! Two terms can have identical (or empty) extensions but completely different intensions (e.g., "unicorn" and "centaur" both have empty extension, but distinct connotations).
• Lexical definitions can be judged TRUE or FALSE based on actual linguistic usage; Stipulative definitions can NEVER be true or false because they are arbitrary proposals.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 88
    ),
    ShortNote(
        id = "logic-u4",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 4",
        title = "Basic Concepts of Critical Thinking: Standards, Principles, Traits & Barriers",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Critical thinking is a disciplined cognitive process of thinking clearly, rationally, fairly, and independently. It integrates intellectual standards, codes of conduct for good argumentation, metacognition, and emotional self-regulation while actively dismantling psychological barriers such as egocentrism, sociocentrism, and relativistic thinking.

🔍 DETAILED BREAKDOWN
1. Meaning and Foundations of Critical Thinking:
   • Etymology & Essence: Derived from the concept of exercising skilled judgment and keen observation.
   • Beyond Raw Intelligence: High IQ or being "smart" does not guarantee critical thinking or impartiality.
   • Core Aim: Arriving at well-reasoned, justifiable conclusions free from the distortions of irrelevant emotions and personal biases.
   • Major Philosophical Definitions:
     - John Dewey: "Active, persistent, and careful consideration of a belief or supposed form of knowledge in the light of the grounds which support it and the further conclusions to which it tends" (emphasizes self-directed questioning, thorough reflection, and evaluating grounds/implications).
     - Edward Glaser: Three core components: (1) an attitude/disposition of thoughtful consideration, (2) knowledge of logical inquiry methods, and (3) skill in applying them. Combines cognitive ability with enduring habit.
     - Robert Ennis: "Reasonable, reflective thinking that is focused on deciding what to believe or do" (adds practical decision-making and action to theoretical evaluation).
     - Richard Paul: Thinking about one's thinking (metacognition) while taking charge of the structures of thought and imposing intellectual standards upon them.
     - Michael Scriven: Skilled, active interpretation and evaluation of observations and communications; a foundational academic literacy.
   • "Criticocreative" Thinking: Critical thinking is NOT purely negative or destructive. It harmonizes critical evaluation (detecting flaws, assessing arguments) with creative thinking (generating alternative hypotheses, imagining alternative scenarios, diverse perspectives, and novel solutions).

2. Universal Standards of Critical Thinking (8 Core Standards):
   1. Clarity: The "gateway" standard—if an expression is unclear, one cannot determine whether it is accurate or relevant. Demands language free from obscurity, muddiness, and vagueness.
   2. Precision: Insisting on exactness, specific details, and precise questions/answers to cut through overgeneralizations.
   3. Accuracy: Ensuring statements reflect genuine factual truth ("Garbage in, garbage out"—flawed facts produce flawed conclusions regardless of logical validity).
   4. Relevance: Confining evidence and considerations strictly to those with genuine logical connection to the issue at hand.
   5. Consistency: Avoiding self-contradiction:
      - Logical Consistency: Not holding mutually contradictory propositions simultaneously.
      - Practical Consistency: Aligning deeds and behavior with stated beliefs (avoiding hypocrisy and self-deception).
   6. Logical Correctness: Drawing conclusions that follow with strict necessity (deductive) or high probability (inductive) from well-founded premises.
   7. Completeness: Preferring deep, thorough, comprehensive analysis over shallow, narrow examinations.
   8. Fairness: Maintaining an open, impartial mindset that treats all relevant viewpoints alike, free from personal bias, self-interest, and preconceptions.

3. Codes of Intellectual Conduct for Effective Discussion:
   • 3.1 Principles of a Good Argument:
     - Structural Principle: Premises must not contradict each other or the conclusion, must avoid invalid forms, and must not assume the conclusion (avoiding circularity / begging the question).
     - Relevance Principle: Reasons must provide genuine evidence counting toward the truth of the conclusion.
     - Acceptability Principle: Premises must be claims that a mature, rational person would accept in light of all available evidence (preferred over elusive "absolute truth").
     - Sufficiency Principle: Reasons provided must be sufficient in weight, number, and kind to justify the conclusion.
     - Rebuttal Principle: Must anticipate, address, and effectively refute or blunt the strongest criticisms and counterarguments.
   • 3.2 Principles of Critical Thinking in Dialogue:
     - Fallibility Principle: Openly acknowledging that one's initial beliefs may be incomplete, flawed, or incorrect.
     - Truth-Seeking Principle: A serious, lifelong commitment to discovering the most defensible truth, requiring active engagement with opposing views.
     - Clarity Principle: Formulating all positions and counter-claims in unambiguous, clear language.
     - Burden of Proof Principle: The obligation to defend a position rests on the person asserting it; shifting the burden of proof is fallacious.
     - Principle of Charity: Reconstructing an opponent's argument in its strongest, most defensible form consistent with their intent before evaluating it.
     - Suspension of Judgment Principle: If evidence is insufficient or equally balanced on both sides, suspend judgment until further evidence emerges.
     - Resolution Principle: An issue is considered resolved when one stance is supported by a structurally sound, relevant, acceptable, and sufficient argument with an effective rebuttal; participants are rationally obligated to accept it unless new evidence appears.

4. Critical Thinkers vs. Uncritical Thinkers:
   • Intellectual Honesty: Acknowledge what they do not know and admit cognitive limitations VS. Pretend to know, ignore blind spots.
   • Response to Complexity: View controversial issues and complexities as exciting challenges VS. Regard complexity as a nuisance or threat to ego.
   • Curiosity & Perseverance: Patient with complexity, willing to do intellectual work VS. Impatient, prefer remaining confused.
   • Basis of Judgment: Base judgments on verifiable evidence, defer when lacking, revise upon new data VS. Rely on first impressions, gut reactions, and self-serving bias.
   • Open-Mindedness: Listen attentively to opposing views VS. Preoccupied with own opinions, rush to refute without listening.
   • Intellectual Independence: Think independently with courage to disagree VS. Succumb to groupthink and uncritical conformity.
   • Emotional Self-Regulation: Practice emotional restraint, think before acting VS. Ruled by immediate feelings and impulsive reactions.

5. Barriers to Critical Thinking (5 Major Obstacles):
   1. Egocentrism (Self-Centered Thinking):
      - Self-Interested Thinking: Accepting/defending beliefs solely because they harmonize with personal advantage or benefit.
      - Superiority Bias (Better-Than-Average Effect): Overrating oneself; viewing one's own intelligence, morality, or competence as superior to what it actually is.
   2. Sociocentrism (Group-Centered Thinking):
      - Group Bias ("Mine-is-better" thinking): Viewing one's own group (ethnic, national, religious, peer) as inherently superior while distrusting outsiders.
      - Conformism: The unthinking tendency to follow the crowd, yield to peer pressure, and surrender judgment to authority.
   3. Unwarranted Assumptions and Stereotypes:
      - Unwarranted Assumption: Taking something for granted without supporting evidence or justification.
      - Stereotype: Generalizing that all members of a group share identical characteristics based on hasty generalizations from small samples or media depictions.
   4. Relativistic Thinking:
      - Subjectivism: Claiming truth is purely a personal matter of opinion ("True for me, but not for you").
      - Cultural Relativism: Claiming truth is determined by whatever a particular society/culture accepts.
      - Moral Relativism: Claiming there are no objective moral truths. (Disagreement does not prove lack of objective truth, nor does relativism ensure genuine tolerance).
   5. Wishful Thinking:
      - Believing something is true merely because one desires or wishes it to be true, inventing comforting illusions to avoid facing harsh realities.

6. Benefits of Critical Thinking:
   • In the Classroom: Promotes higher-order cognitive analysis over rote memorization; provides transferable skills across sciences and humanities.
   • In Personal Life: Prevents irrational, impulsive personal, financial, career, and healthcare decisions.
   • In Democratic & Civic Life: Equips citizens to see through political propaganda and emotional manipulation, vote responsibly, and reduce sectarian prejudice.
   • For Personal Liberation: Frees the mind from unexamined cultural dogmas, enabling individuals to lead a truly "examined life" directed by independent reason.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The "Gateway" Standard is CLARITY: without clarity, questions of accuracy, precision, and relevance cannot even be evaluated.
• Principle of Charity: Always evaluate an opponent's argument in its STRONGEST possible version, rather than setting up an easy-to-defeat caricature.
• Distinguish Egocentrism (individual self-interest & superiority bias) from Sociocentrism (group bias & conformism).
• Relativistic Thinking is a major cognitive barrier: the fact that people or cultures disagree on a topic does NOT mean there is no objective truth.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 92
    ),
    ShortNote(
        id = "logic-u5",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 5",
        title = "Informal Fallacies: Relevance, Weak Induction, Presumption & Ambiguity",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
A fallacy is a defect in reasoning that creates an illusion of logical plausibility while violating the standards of a good argument (relevance, acceptability, sufficiency, rebuttal). While formal fallacies involve structural arrangements in deductive arguments, informal fallacies arise from defects in content, meaning, and linguistic context across both deductive and inductive reasoning.

🔍 DETAILED BREAKDOWN
1. Fallacies in General:
   • Definition: A flaw, defect, or error in reasoning other than merely possessing false premises.
   • Illusion of Goodness: Makes a defective argument psychologically persuasive or deceptively acceptable.
   • Impact on Soundness and Cogency:
     - Deductive argument containing a fallacy = Automatically UNSOUND.
     - Inductive argument containing a fallacy = Automatically UNCOGENT.
   • Violation of Argument Standards:
     - Relevance: Premises must bear a genuine logical connection to the conclusion.
     - Acceptability: Premises must be reasonable to a mature, rational person.
     - Sufficiency: Premises must provide adequate weight, number, and kind of reasons.
     - Rebuttal: Argument must anticipate and effectively blunt counterarguments.
   • Formal vs. Informal Fallacies:
     - Formal Fallacies: Result strictly from structural defects in the form of deductive arguments; identifiable by mere inspection of form (e.g., Affirming the Consequent, Denying the Antecedent).
     - Informal Fallacies: Result from defects in content, language, meaning, or context; cannot be identified by form alone and occur in both deductive and inductive arguments.

2. Fallacies of Relevance (Premises are logically irrelevant to conclusion; Non Sequitur):
   • 1. Appeal to Force (Argumentum ad Baculum / Appeal to the "Stick"):
     - Threatening physical or psychological harm/coercion to force acceptance of a conclusion.
     - Threat is logically irrelevant to whether the conclusion is actually true.
   • 2. Appeal to Pity (Argumentum ad Misericordiam):
     - Evoking compassion, sympathy, or pity to secure agreement instead of presenting evidence.
     - Exception: Legitimate in humanitarian appeals where victims bear no fault for their situation.
   • 3. Appeal to the People (Argumentum ad Populum):
     - Exploiting desires to belong, feel admired, fit in, or avoid social exclusion.
     - Direct Approach: Exciting mob mentality, mass enthusiasm, or collective hysteria in a crowd.
     - Indirect Approach:
       * Bandwagon: Pressuring conformity because "everyone else" does it ("Majority accepts X, so you must").
       * Appeal to Vanity: Associating a claim/product with an admired celebrity or glamorous icon.
       * Appeal to Snobbery: Appealing to desire to feel elite, distinguished, or superior to common crowds.
   • 4. Argument Against the Person (Argumentum ad Hominem):
     - Two-party dialogue where the respondent attacks the opponent's person rather than their argument:
       * Ad Hominem Abusive: Direct personal insults and character assassination.
       * Ad Hominem Circumstantial: Attacking personal circumstances, employment, or vested interests.
       * Tu Quoque ("You Too"): Accusing opponent of hypocrisy or inconsistency ("You smoke, so why tell me to quit?").
     - Non-Fallacious Exception: Legitimate during courtroom cross-examination when assessing witness credibility and veracity.
   • 5. Accident:
     - Misapplying a general rule to an atypical specific case that it was never intended to govern (e.g., applying freedom of speech to inciting riots).
   • 6. Straw Man:
     - Distorting, exaggerating, or caricaturing an opponent's position to easily demolish it, then claiming their original position is refuted.
   • 7. Missing the Point (Ignoratio Elenchi):
     - Premises logically support one clear conclusion, but arguer instead draws a completely different, unwarranted, non-sequitur conclusion.
   • 8. Red Herring:
     - Subtly shifting the topic to an exciting, unrelated, or sensational issue (scandal, gossip) to divert attention and claiming victory.
   • Crucial Trio Distinction:
     - Straw Man: Distorts opponent's real argument (requires 2 arguers).
     - Red Herring: Changes the topic entirely to a new issue.
     - Missing the Point: Uses original premises but draws an illegitimate conclusion.

3. Fallacies of Weak Induction (Premises provide inadequate probabilistic evidence):
   • 1. Appeal to Unqualified Authority (Argumentum ad Verecundiam):
     - Citing a source lacking legitimate training/expertise in the subject under dispute, or one having a conflict of interest.
   • 2. Appeal to Ignorance (Argumentum ad Ignorantiam):
     - Concluding a proposition is true because nobody has proven it false, or false because nobody has proven it true ("Absence of evidence is not evidence of absence").
     - Legal Exception: Presumption of innocence in criminal law is a protected human right, not a fallacy.
   • 3. Hasty Generalization (Converse Accident):
     - Generalizing about an entire population from an unrepresentative, biased, or tiny sample.
   • 4. False Cause:
     - Post Hoc Ergo Propter Hoc: Assuming temporal sequence implies causation ("After this, therefore because of this").
     - Non Causa Pro Causa: Mistaking coincidental correlation for genuine causation.
     - Oversimplified Cause: Portraying one minor factor as the sole cause of a complex phenomenon.
   • 5. Slippery Slope:
     - Claiming without justification that an innocent first step will trigger an inevitable, catastrophic chain reaction.
   • 6. Weak Analogy:
     - Drawing an inductive conclusion based on superficial, trivial similarities while overlooking crucial differences.

4. Fallacies of Presumption (Premises presume the very claim to be proved):
   • 1. Begging the Question (Petitio Principii):
     - Assuming the truth of the conclusion from the start; circular reasoning or leaving out a questionable key premise.
   • 2. Complex Question:
     - Asking a loaded question that embeds an unproven presupposition, such that either "yes" or "no" forces an admission of guilt ("Have you stopped cheating?").
   • 3. False Dichotomy (Either/Or Fallacy):
     - Presenting two alternatives as mutually exhaustive when other reasonable possibilities exist, eliminating one to force the other.
   • 4. Suppressed Evidence:
     - Deliberately concealing or omitting vital countervailing evidence that contradicts the conclusion.

5. Fallacies of Ambiguity and Grammatical Analogy:
   • Fallacies of Ambiguity:
     - Equivocation: Shifting the meaning of a key term across premises and conclusion ("A feather is light; what is light is not dark...").
     - Amphiboly: Ambiguity caused by faulty sentence grammar, loose syntax, or misplaced modifiers.
   • Fallacies of Grammatical Analogy:
     - Composition: Erroneously transferring an attribute of individual parts to the whole entity ("Each component is light, so machine is light").
     - Division: Erroneously transferring an attribute of the whole entity to individual parts ("The corporation is wealthy, so each janitor is wealthy").

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Accident vs Hasty Generalization: Accident goes from General rule -> Specific exceptional case (misapplication). Hasty Generalization goes from Specific atypical case -> General sweeping rule (overgeneralization).
• Composition vs Division: Parts -> Whole = Composition; Whole -> Parts = Division.
• Red Herring vs Straw Man: Straw Man distorts the opponent's argument (must involve two arguers); Red Herring changes the topic entirely.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 95
    ),
    ShortNote(
        id = "logic-u6",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 6",
        title = "Categorical Propositions: Components, Distribution, Squares & Operations",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
A categorical proposition is an assertive statement that relates two classes—the Subject class (S) and the Predicate class (P)—by asserting total or partial inclusion or exclusion. Classical Aristotelian and modern Boolean logic analyze these propositions through quality, quantity, distribution, Venn diagrams, squares of opposition, and immediate logical inferences.

🔍 DETAILED BREAKDOWN
1. General Introduction & Standard Forms:
   • Proposition: Informational meaning of a declarative sentence possessing a truth value (true or false).
   • Categorical Proposition: Asserts that all or part of the class denoted by the Subject term (S) is included in or excluded from the class denoted by the Predicate term (P).
   • Four Standard Forms (A, E, I, O from Latin "AffIrmo" [I affirm] and "nEgO" [I deny]):
     - A (Universal Affirmative): "All S are P" (Total inclusion of S in P).
     - E (Universal Negative): "No S are P" (Total exclusion of S from P).
     - I (Particular Affirmative): "Some S are P" (Partial inclusion of S in P).
     - O (Particular Negative): "Some S are not P" (Partial exclusion of S from P).
   • Four Constituent Components (in strict order):
     1. Quantifier: Specifies scope ("All", "No", "Some").
     2. Subject Term (S): Designates the primary class.
     3. Copula: Linking verb ("are" or "are not").
     4. Predicate Term (P): Designates the second class.
     - Example: [Quantifier: All] [Subject: philosophers] [Copula: are] [Predicate: critical thinkers].

2. Attributes: Quality, Quantity, and Distribution:
   • 1. Quality (Class Inclusion vs Exclusion):
     - Affirmative: Asserts class inclusion (A and I).
     - Negative: Asserts class exclusion (E and O).
   • 2. Quantity (Scope of Subject Class):
     - Universal: Encompasses every member of S without exception (A and E: "All S", "No S").
     - Particular: Encompasses at least one member of S (I and O: "Some S", where "some" means at least one).
   • 3. Distribution of Terms:
     - Definition: A term is distributed if the proposition makes an assertion about EVERY member of the class denoted by that term; otherwise it is undistributed.
     - A ("All S are P"): Subject (S) is DISTRIBUTED; Predicate (P) is UNDISTRIBUTED.
     - E ("No S are P"): BOTH Subject (S) and Predicate (P) are DISTRIBUTED.
     - I ("Some S are P"): NEITHER Subject (S) nor Predicate (P) is distributed.
     - O ("Some S are not P"): Subject (S) is UNDISTRIBUTED; Predicate (P) is DISTRIBUTED.
     - Mnemonic: "Universals distribute Subjects (A, E); Negatives distribute Predicates (E, O)" (Any Student Is Puzzled).

3. Venn Diagrams & Squares of Opposition:
   • 3.1 Two-Circle Venn Diagrams (Left = S, Right = P):
     - Shading: Region is EMPTY (contains zero members).
     - "X": Region is NON-EMPTY (contains at least one member).
     - A (All S are P): Shade S outside P (only S region is empty).
     - E (No S are P): Shade the middle overlap (S ∩ P is empty).
     - I (Some S are P): Place an "X" in the middle overlap (S ∩ P).
     - O (Some S are not P): Place an "X" in the S region outside P.
   • 3.2 Existential Import:
     - Boolean (Modern) Standpoint: Universal statements (A and E) have NO existential import; they are hypothetical ("If anything is an S, it is a P"). Operates without assuming actual real-world existence (works for null classes like unicorns).
     - Aristotelian (Traditional) Standpoint: Assumes universal propositions denote actually existing entities.
   • 3.3 Modern Square of Opposition (Boolean):
     - Only CONTRADICTION survives: A ↔ O and E ↔ I have opposite truth values.
     - All other relationships (contrary, subcontrary, subalternation) are invalid without separate proof of existence.
   • 3.4 Traditional Square of Opposition (Aristotelian):
     - Contradictories (A & O; E & I): Opposite truth values (cannot both be true, cannot both be false).
     - Contraries (A & E): Cannot both be true simultaneously (at least one is false). If one is True, the other is False; but if one is False, the other is logically UNDETERMINED.
     - Subcontraries (I & O): Cannot both be false simultaneously (at least one is true). If one is False, the other is True; but if one is True, the other is logically UNDETERMINED.
     - Subalternation (A -> I; E -> O):
       * Truth flows DOWNWARD: If universal (A/E) is True, particular (I/O) is necessarily True.
       * Falsity flows UPWARD: If particular (I/O) is False, universal (A/E) is necessarily False.
       * If universal is False, particular is Undetermined; if particular is True, universal is Undetermined.

4. Evaluating Immediate Inferences and Logical Operations:
   • Definition: Immediate inference moves directly from a single categorical premise to a conclusion.
   • 1. Conversion:
     - Procedure: Switch positions of Subject and Predicate terms (S ↔ P).
     - Valid for: E ("No S are P" ⟹ "No P are S") and I ("Some S are P" ⟹ "Some P are S").
     - Invalid for: A and O (Results in Fallacy of Illicit Conversion).
   • 2. Obversion:
     - Procedure: Change the quality (Affirmative ↔ Negative) and replace the Predicate term with its term complement (non-P).
     - Valid for: ALL FOUR standard forms (A, E, I, O), preserving identical truth values:
       * A ("All S are P") ⟹ E ("No S are non-P")
       * E ("No S are P") ⟹ A ("All S are non-P")
       * I ("Some S are P") ⟹ O ("Some S are not non-P")
       * O ("Some S are not P") ⟹ I ("Some S are non-P")
   • 3. Contraposition:
     - Procedure: Switch positions of S and P AND replace both with their term complements (non-P and non-S).
     - Valid for: A ("All S are P" ⟹ "All non-P are non-S") and O ("Some S are not P" ⟹ "Some non-P are not non-S").
     - Invalid for: E and I (Results in Fallacy of Illicit Contraposition).
   • Summary of Operations:
     - Conversion: Valid for E & I only.
     - Obversion: Valid for ALL (A, E, I, O).
     - Contraposition: Valid for A & O only.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Distribution Rule: "Universals distribute Subject; Negatives distribute Predicate." E distributes BOTH; I distributes NEITHER.
• Traditional Square: In Contraries (A & E), both cannot be TRUE; in Subcontraries (I & O), both cannot be FALSE.
• Subalternation Direction: TRUTH flows DOWN (Universal -> Particular); FALSITY flows UP (Particular -> Universal).
• Operations to Remember: Obversion is universally valid for all four forms; Conversion works only on E & I; Contraposition works only on A & O.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 93
    ),
    ShortNote(
        id = "logic-u7",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 7",
        title = "Rules of Inference, Natural Deduction & Scientific Reasoning",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Natural deduction validates arguments step-by-step using elementary rules of inference and replacement. Critical thinking applies these rational methods to empirical science, identifying formal fallacies and cognitive biases.

🔍 DETAILED BREAKDOWN
1. The Eight Elementary Rules of Inference:
   • 1. Modus Ponens (MP):
     p → q
     p
     ∴ q  (Affirming the antecedent)
   • 2. Modus Tollens (MT):
     p → q
     ~q
     ∴ ~p  (Denying the consequent)
   • 3. Hypothetical Syllogism (HS):
     p → q
     q → r
     ∴ p → r  (Chain reasoning)
   • 4. Disjunctive Syllogism (DS):
     p ∨ q
     ~p
     ∴ q  (Ruling out an alternative)
   • 5. Constructive Dilemma (CD):
     (p → q) ∧ (r → s)
     p ∨ r
     ∴ q ∨ s
   • 6. Simplification (Simp):
     p ∧ q ⊢ ∴ p (or q)
   • 7. Conjunction (Conj):
     p
     q
     ∴ p ∧ q
   • 8. Addition (Add):
     p ⊢ ∴ p ∨ q (any arbitrary statement can be disjoined to a known truth).

2. Two Major Formal Fallacies to Avoid:
   • Fallacy of Affirming the Consequent:
     p → q
     q
     ∴ p  (INVALID! e.g., If it rains, the ground is wet. The ground is wet. Therefore, it rained - could be a sprinkler).
   • Fallacy of Denying the Antecedent:
     p → q
     ~p
     ∴ ~q  (INVALID! e.g., If you study, you pass. You did not study. Therefore, you will not pass).

3. Scientific Reasoning & Hypothetico-Deductive Method:
   • Scientific Inquiry Cycle: Observation -> Inductive Hypothesis -> Deductive Prediction -> Empirical Testing.
   • Karl Popper's Principle of Falsifiability:
     - A genuine scientific hypothesis must be formulated such that it is capable of being empirically tested and potentially refuted (falsified).
     - Science progresses through bold conjectures and rigorous attempts at refutation, not merely accumulating confirming instances.

4. Cognitive Biases & Barriers to Critical Thinking:
   • Confirmation Bias: Tendency to search for, interpret, and recall evidence supporting preexisting beliefs while ignoring contrary evidence.
   • Availability Heuristic: Overestimating the likelihood of events based on how easily dramatic instances come to mind (e.g., plane crashes).
   • Egocentric & Sociocentric Thinking: Believing something is true merely because "I want to believe it" or because "my peer group/society accepts it".

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Modus Ponens affirms the ANTECEDENT to get the consequent. Affirming the CONSEQUENT is a major formal fallacy!
• In natural deduction, rules of inference operate only on ENTIRE lines, whereas rules of replacement (equivalences) can apply to sub-expressions.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 89
    ),

    // =========================================================================
    // 5. COMMUNICATIVE ENGLISH (c5, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "eng-u1",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 1",
        title = "Study Skills: Lecture Note-Taking, Reading Strategies & Grammar",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Unit 1 (FLEn 1011) establishes the foundational study competencies essential for university academic success. It integrates systematic lecture listening and selective note-taking habits, purposeful academic reading (skimming, scanning, intensive study), advice grammar structures (imperatives, should, ought to, had better), and the functional mastery of the Present Perfect tense versus the Simple Past.

🔍 DETAILED BREAKDOWN
1. Listening: What is a Lecture & Effective Study Habits:
   • The Role of Lecture Listening & Note-Taking:
     - University students receive vast amounts of disciplinary knowledge through reading assignments and live lectures.
     - Because human working memory cannot retain all spoken or read details, systematic note-taking is indispensable for exam revision and academic performance.
   • Seven Core Habits of Successful University Students (Survey Insights):
     1. Distribute learning over time; avoid cramming all material into a single marathon session.
     2. Schedule dedicated study blocks in advance and adhere to consistent study routines.
     3. Establish clear, specific, achievable goals for every study session.
     4. Avoid procrastinating on scheduled study times.
     5. Tackle the most challenging or difficult subject first when mental energy and focus are at their peak.
     6. Review lecture notes before starting assignments, and revisit class notes over weekends.
     7. Eliminate environmental distractions (phone, noise) and participate actively in productive peer study groups.
   • Key Strategies for Taking Lecture Notes:
     - Pre-Lecture Preparation: Read assigned textbook sections prior to class to build a conceptual reference framework.
     - Physical Positioning: Sit near the front of the lecture hall to maximize visual/auditory concentration and minimize distractions.
     - Focus on Introductions: Pay close attention to introductory remarks to discern the structural outline and theme of the lecture.
     - Identify Lecturer Cues: Watch for verbal markers, vocal inflection/emphasis, and physical gestures that signal core concepts vs. incidental details.
     - Selective Recording: Never attempt word-for-word (verbatim) transcription—it causes students to fall behind. Record main concepts and supporting evidence using concise phrases, abbreviations, and symbols.
     - Immediate Post-Lecture Review: Review and edit notes as soon as possible after the lecture to fill in gaps and consolidate memory traces.

2. Grammar Focus: Modals and Infinitives for Giving Advice:
   • Structures for Recommendations and Advice:
     - Bare Infinitive (Imperative): Base verb form used for direct instructions and practical guidelines (e.g., "Sit near the front of the hall").
     - Should / Ought to: [should / ought to + base verb] used for standard advice indicating what is desirable or recommended (e.g., "You should review your notes daily").
     - Had better: [had better + base verb] expresses stronger, urgent advice indicating significant importance or negative consequences if ignored (e.g., "You had better read before the lecture, or you will fall behind").
   • Negative Forms & Structural Constraints:
     - should not + base verb ("You should not transcribe verbatim").
     - ought not to + base verb.
     - had better not + base verb ("You had better not pack too much"; beware: avoid incorrect forms like "had not better" or "had better to").
   • Infinitive Suggestions: Using introductory verbs like "try to" + infinitive ("Try to prepare your mind in advance").

3. Reading: Reading for Study:
   • Nature & Purpose of Academic Reading:
     - The primary medium for acquiring disciplinary knowledge across engineering, medicine, humanities, and sciences.
     - Primary Goal: To extract ideas and evidence, assimilate them into an existing cognitive framework, and articulate that understanding in seminars and exams.
   • Reading According to Specific Purpose:
     - Reading for Practical Application: Following technical manuals or procedural guides to operate equipment or resolve practical tasks.
     - Reading for Escapism / Pleasure: Fast reading of narrative fiction without the demand of memorizing analytical details.
     - Skimming: Rapidly glancing across texts, headings, and topic sentences to capture the general overview, core gist, or main developments without pausing for details.
     - Scanning: Swiftly searching through a text (indexes, data tables, headlines) to locate a specific datum, name, or term while ignoring irrelevant material.
     - Detailed Academic Study: Close, slow, analytical reading of textbooks and research articles to evaluate arguments, inspect assumptions, and master complex theories.
   • Avoiding the "Beginning-to-End" Trap:
     - Mistake: Treating academic textbooks like novels by opening chapter one and reading linearly until cognitive fatigue sets in.
     - Remedy: Active, strategic reading—previewing headings, formulating questions, scanning for key concepts, and taking structured notes. Reading is an active cognitive process of interpreting and evaluating meaning!

4. Grammar Focus: Present Perfect Tense:
   • Syntactic Form: Subject + have / has + Past Participle (V3).
     - Regular verbs take -ed (developed, identified, cleaned); irregular verbs take unique forms (done, built, seen, written).
   • Five Core Functions of Present Perfect:
     1. Unspecified Past Time: Action occurred at an indefinite, unstated time before now ("Researchers have identified multiple study strategies").
     2. Present Result of a Past Action: Action completed in the past has a direct, observable consequence in the present ("I have lost my notes [so I cannot study now]").
     3. Continuing State: Action or condition that began in the past and persists into the present ("Classes have already started").
     4. Life Experience: Actions that have occurred once, multiple times, or never up to the present moment ("I have never visited Moyale").
     5. Recent Completion: Actions completed immediately prior to speaking ("The instructor has just finished the lecture").
   • Present Perfect vs. Simple Past (High-Yield Exam Contrast):
     - Time Orientation: Present Perfect links the past to the present (indefinite/unspecified time); Simple Past is confined strictly to a completed past disconnected from the present.
     - Time Adverbials:
       * Present Perfect: Used with indefinite markers (already, yet, ever, never, just, so far, up to now, since).
       * Simple Past: Demands definite, specific past time markers (yesterday, last year, two months ago, in 1992, at 3 PM).
     - Cardinal Error: NEVER combine the Present Perfect with specific past time adverbs!
       * Incorrect: "I have read this book last year."
       * Correct: "I read this book last year."

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Advice Grammar Traps: The negative of "had better" is strictly "had better not" (NEVER "had not better" or "had better not to").
• Skimming = Reading for GIST / general idea; Scanning = Searching for SPECIFIC target information (dates, names, figures).
• Present Perfect Trap: If a sentence includes a specific past time marker ("yesterday", "in 2020", "last semester"), you MUST use Simple Past, NOT Present Perfect!
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 86
    ),
    ShortNote(
        id = "eng-u2",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 2",
        title = "Health & Fitness: Athlete Biographies, Conditionals & Context Clues",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Unit 2 (FLEn 1011) focuses on the communicative theme of Health and Fitness. It develops listening competence through athlete biographical profiles, masters the syntax of Conditionals (Types I, II, III and alternative conjunctions), analyzes expository reading on team sports and classification of athletic disciplines, and applies contextual inferencing to deduce lexical meanings.

🔍 DETAILED BREAKDOWN
1. Listening: Sports Stars & Professional Biographies:
   • Profile Focus: Engaging with career narratives, achievements, and biographies of world-renowned athletes (e.g., Zinedine Zidane, Derartu Tulu).
   • Four Key Dimensions for Describing Sports Figures:
     1. Physical Characteristics: Observable physical traits (height, build, stamina, endurance).
     2. Personal Attributes: Psychological and interpersonal qualities (discipline, sportsmanship, humility, sociability, mental grit).
     3. Career Milestones: Playing positions, clubs/national teams represented, historic transfers, and major tournament appearances.
     4. Achievements & Honors: Broken world records, Olympic gold medals, championship titles, and lifetime athletic honors.
   • Note-Taking Application: Formulating and extracting specific factual data using targeted WH-questions (When, Where, What for, How long).

2. Grammar Focus: Conditionals (Types I, II, III & Alternatives):
   • Sentence Architecture: Subordinate condition clause (if-clause) + Result clause (main clause).
   • The Three Core Conditional Types:
     1. Type I (Real / Likely Condition):
        - Form: If + Simple Present, ... will / can / may + base verb.
        - Meaning: Real, probable, and possible future situations.
        - Example: "If I meet the coach tomorrow, I will ask for training advice."
     2. Type II (Unreal / Hypothetical Condition):
        - Form: If + Simple Past, ... would / could / might + base verb.
        - Meaning: Unreal, imaginary, or contrary-to-fact present or future situations.
        - Example: "If I had more leisure time, I would join the university football club."
        - Formal Subjunctive "Were": Formal English employs "were" for all persons (including I/he/she/it):
          * "If I were you, I would consult a physician before running a marathon."
     3. Type III (Past Counterfactual / Impossible Condition):
        - Form: If + Past Perfect (had + V3), ... would / could / might have + Past Participle (V3).
        - Meaning: Past counterfactual conditions that did not happen; expressing regret, excuses, or alternative outcomes in retrospect.
        - Example: "If Derartu had trained harder on hills, she would have won the race earlier."
   • Alternative Conditional Conjunctions (Beyond "If"):
     - Provided / Provided that: Sets an explicit prerequisite ("I could pass the fitness test provided that I maintain cardiovascular training").
     - As long as: Emphasizes a continuous, unbroken condition ("Nobody suffers from obesity as long as they eat balanced diets and exercise regularly").
     - On condition that: Formal legalistic stipulation of terms ("He joined the team on condition that he maintained a 3.0 GPA").

3. Reading: Health and Fitness (The Case for Team Sports):
   • Core Psychological & Physical Benefits of Team Sports:
     - Consistency & Accountability: Scheduled practices and league matches create social accountability that sustains regular exercise habits far better than solitary routines.
     - Healthy Competition & Sportsmanship: Striving for victory while learning to accept defeat with emotional maturity, humility, and resilience.
     - Social & Emotional Fulfillment: Cultivates camaraderie, mutual trust, effective communication, and lasting interpersonal bonds.
   • Six-Category Classification of Sports Disciplines:
     1. Action / Combat Sports: Football, rugby, karate, boxing, hockey, Taekwondo.
     2. Ball & Hand-Eye Coordination Sports: Basketball, volleyball, tennis, baseball, cricket, lacrosse.
     3. Precision & Target Sports: Archery, bowling, billiards/pool, target shooting, ping pong.
     4. Balance & Rhythm Sports: Gymnastics, figure skating, competitive dancing, cheerleading.
     5. Aquatic Sports: Swimming, water polo, synchronized diving.
     6. Speed & Velocity Sports: Track sprints, distance running, alpine skiing, snowboarding, sailing, motor racing.

4. Vocabulary: Guessing Meaning from Context:
   • Contextual Inferencing: Using syntactic structures, neighboring modifier words, and thematic clues to deduce word definitions without reaching for a dictionary.
   • Target Textual Deductions:
     - "Regular" -> Scheduled, recurring at fixed, dependable intervals (e.g., regular physical drills).
     - "Sportsmanship" -> Fair play, respect for opponents, and demonstrating grace in both victory and defeat.
     - "A bunch of" -> An informal collection or diverse group of items/activities.
   • Three Dimensions of Word Knowledge:
     1. Orthography (accurate spelling).
     2. Grammatical Inflections (e.g., jog -> jogged -> jogging).
     3. Syntactic Usage (placing words in natural, grammatically sound sentence positions).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Subjunctive "Were" Rule: In Type II conditionals, always prefer "were" over "was" in standard exams ("If I were you...", NOT "If I was you...").
• Type III Match: If the if-clause uses "had + V3", the main clause MUST use "would have + V3" (NEVER mix with Type II "would + base verb").
• Alternative conjunctions ("provided that", "as long as", "on condition that") obey the EXACT same tense rules as standard "if" clauses!
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 89
    ),
    ShortNote(
        id = "eng-u3",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 3",
        title = "Cultural Values: Cultural Tourism, Tense Contrasts & The Awramba Community",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Unit 3 (FLEn 1011) explores Cultural Values through the dual lenses of cultural tourism and the social experiment of the Awramba utopian community in Ethiopia. It integrates listening on cultural tourism economics and risks, systematic contrasts of four fundamental verb tenses (Present Simple, Past Simple, Present Perfect, Past Perfect), grammar acquisition strategies, and contextual academic vocabulary.

🔍 DETAILED BREAKDOWN
1. Listening: Cultural Tourism & Conceptual Foundations:
   • Culture & Cultural Values Defined:
     - Culture: The shared beliefs, social customs, institutions, artifacts, and behavioral norms that define a community and shape how members interpret reality.
     - Cultural Values: The fundamental ideals, ethical principles, and moral standards upon which a community exists and guides human conduct.
   • Cultural Tourism:
     - A major travel sector where domestic and international visitors travel to historic towns, heritage sites, religious monuments, and indigenous communities to experience unique living cultures.
   • Dual Dimensions of Cultural Tourism:
     - Economic & Social Benefits: Generates substantial revenue, creates local employment, promotes heritage conservation, encourages intercultural exchange, and enriches visitors' personal identity.
     - Risks & Criticisms: Danger of commodifying or diluting sacred ancestral traditions for commercial display; cultural tourists function as "advance guards" for mass tourism that can disrupt and destabilize vulnerable local communities.

2. Grammar Focus: Verb Tenses in Contrast:
   • Contrasting the Four Primary Narrative & Academic Tenses:
     1. Present Simple (Base verb / -s / -es):
        - Universal truths and scientific facts ("Water boils at 100°C").
        - Habitual actions and recurring routines ("They weave textiles daily").
        - Permanent or long-lasting situations ("The Awramba community lives in Fogera").
     2. Past Simple (V2 / -ed or irregular):
        - Actions completed at a definite, specific point in the past ("Zumra Nuru founded the community in 1980").
        - Past historical events or completed historical states of affairs.
     3. Present Perfect (have / has + V3):
        - Past actions producing an observable, direct present result ("They have built modern weaving workshops").
        - Cumulative life experiences at unspecified times ("Scholars have praised their gender equality").
        - Actions starting in the past and continuing into the present moment ("They have lived in Fogera since 1980").
     4. Past Perfect (had + V3):
        - An action that occurred and was completed BEFORE another specified event in the past ("Earlier past").
        - Chronological order marker: Essential for precise academic narration (e.g., "The villagers were skeptical because they had not witnessed gender parity before").

3. Strategies for Enhancing English Grammar Mastery:
   • Extensive Reading: Reading authentic English literature, academic articles, and quality journalism to internalize syntactic patterns naturally.
   • Noticing in Context: Paying conscious attention to grammar choices while reading and listening.
   • Personalized Rule Application: Writing original model sentences illustrating specific grammatical rules.
   • Systematic Error Analysis: Diagnosing recurring grammatical mistakes through self-editing and paragraph revision.
   • Targeted Practice: Utilizing grammar handbooks and completing focused drills on diagnosed weaknesses.

4. Reading: The Awramba Community and Its Quest for Utopia:
   • Establishment & Location:
     - Founded in 1980 by Zumra Nuru and 19 visionary followers in Fogera woreda, South Gondar Zone (Amhara Region), ~73 km east of Bahir Dar.
     - Etymology: "Awramba" translates to "Top of the Hill" in Amharic.
   • Core Philosophy: An intentional cooperative community committed to eliminating poverty, ignorance, and gender oppression through diligence, mutual solidarity, and strict egalitarianism.
   • Five Distinctive Cultural & Institutional Practices:
     1. Radical Gender Equality: Total equality between men and women; complete abolition of gender-based division of labor (men cook, wash, and care for children; women weave, farm, and lead).
     2. Ethical Code (Absence of Institutional Religion): Members belong to no organized religion (neither Christian nor Islamic); their moral code is founded on hard work, mutual benevolence, absolute honesty, and treating theft as an intolerable vice.
     3. Comprehensive Social Security: Democratic committees manage healthcare, elder support, patient care, early childhood nurseries, and adult literacy programs.
     4. Marriage & Family Reform: Outlaws regional early child marriage; girls marry only at or above 18, and boys at or above 22.
     5. Economic Resilience: Initially ostracized and denied fertile arable land by neighbors due to nonconformist ideology; settled on malaria-prone terrain and successfully diversified into modern textile weaving, spinning, and grain milling.

5. Contextual Academic Vocabulary:
   • Norms: Accepted social rules, standards, and behavioral expectations within a group.
   • Egalitarianism: The philosophical belief in the fundamental equality of all people in status, rights, and opportunities.
   • Alleviate: To make pain, poverty, or suffering less severe or more bearable.
   • Lauded: Highly praised, acclaimed, or publicly commended.
   • Obscene: Repugnant, morally offensive, or deeply unacceptable.
   • Diligent: Exhibiting persistent, attentive, and thorough effort in work.
   • Ostracized: Excluded, banished, or cast out from social participation by a community.
   • Initiative: An innovative strategy or plan devised to resolve a pressing social problem.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Past Perfect vs. Simple Past: Use Past Perfect (had + V3) for the EARLIER of two past actions, and Simple Past (V2) for the SUBSEQUENT past action.
• Awramba Exam Points: Founded by Zumra Nuru in 1980 in Fogera; key features are absolute gender equality, no institutional religion, marriage reform (18 for females, 22 for males), and textile weaving.
• Context Clues: Match vocabulary precisely (e.g., "lauded" = praised; "alleviate" = lessen/mitigate; "ostracized" = socially excluded).
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 88
    ),
    ShortNote(
        id = "eng-u4",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 4",
        title = "Wildlife: Human-Animal Coexistence, Denotation/Connotation & Parks",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Unit 4 (FLEn 1011) explores Wildlife through human-wildlife interactions, African ecological biodiversity, lexical semantics (denotative vs. connotative meanings), and the application of conditional sentences (Types I, II, III) contextualized within Ethiopian national parks and endemic wildlife preservation.

🔍 DETAILED BREAKDOWN
1. Listening: Human-Wildlife Interaction & Coexistence:
   • Spectrum of Interaction: Ranges from positive ecological harmony (ecotourism, biodiversity maintenance) to acute Human-Wildlife Conflict (HWC).
   • Drivers of Conflict: Rapid human population expansion causing direct competition with wild animals over shared resources (agricultural land, water, grazing territory, habitat space).
   • Destructive Consequences: Severe crop destruction, livestock predation, habitat fragmentation, risks to human life, and retaliatory poaching or extinction of endangered species.
   • Meaning of Coexistence:
     - Etymology: The prefix "co-" denotes "together" or "jointly".
     - Coexistence signifies living together in sustainable peace, mutual tolerance, and ecological balance without conflict.

2. Reading: Africa's Wild Animals & Ecosystems:
   • Profile of Four Prominent African Wildlife Species:
     1. Lemur:
        - Habitat: Madagascar (strictly endemic; world's 4th largest island).
        - Diet: Herbivorous/Vegetarian (wild figs, bananas, canopy fruits).
        - Traits: Monkey-like, large nocturnal round eyes, pointed muzzle, long tail, woolly coat; spans from the indri (~95 cm) to tiny mouse lemur (~15 cm); all 25 species are currently endangered.
     2. Mountain Gorilla:
        - Habitat: Dense volcanic slopes of Rwanda, DR Congo, and Uganda.
        - Diet: Herbivorous (forest fruits, foliage, berries).
        - Traits: Largest of all great apes; organized in small troops led by a dominant silverback male; too heavy to climb trees; peaceful, gentle, highly intelligent, and social.
     3. Forest Elephant:
        - Habitat: Congo Basin tropical rainforests.
        - Diet: Herbivorous forest vegetation.
        - Traits: Distinct from the savanna/bush elephant; reaches up to 250 cm in shoulder height; tusks are visibly thinner, shorter, and yellowish.
     4. Chimpanzees & Baboons:
        - Habitat: Tropical rainforest canopy and woodlands.
        - Diet: Omnivorous/Frugivorous (fruits, tender leaves, nuts, insects).
        - Traits: Highly sophisticated tool users (twigs to extract termites, stone/branch anvils to crack hard nuts); travel in cohesive troops (~20 members); sleep in tree nests at night.
   • Endemism Concept: An endemic species is an organism whose natural distribution is strictly confined to a specific geographic region and found nowhere else on Earth.
   • Rainforest Geography: Over 80% of Africa's rainforests are located within the Congo Basin, spanning from the Atlantic west coast to the highlands of Kenya and Tanzania.

3. Vocabulary: Denotative and Connotative Meanings:
   • Denotative Meaning (Denotation):
     - The literal, objective, dictionary definition of a word, devoid of emotional coloring or subjective bias.
     - Example: "Monkey" denotes a specific primate mammal; "group", "club", and "clique" share the same literal denotation ("a gathering of more than one individual").
   • Connotative Meaning (Connotation):
     - The secondary emotional overtones, cultural associations, and social values evoked by a word.
     - Categorization of Connotations:
       * Positive Connotations: "Unique" (admired for distinctiveness), "economical" (prudent with resources), "mother" (nurturing care, warmth).
       * Negative Connotations: "Childish" (immature), "skinny" (unhealthily thin), "clique" (exclusionary, snobbish social circle), "snake" (treacherous/deceitful person).
       * Neutral Connotations: "Group" (general collection), "adolescent" (biological age phase).
   • Communicative Importance: Choosing words based solely on denotation while ignoring negative connotations produces unintentional offense or semantic ambiguity in academic discourse.

4. Grammar Focus: Conditionals Applied to Ethiopian National Parks:
   • Ethiopian National Parks & Associated Wildlife:
     - Semien Mountains National Park: Walia ibex, Gelada baboon, Caracal.
     - Bale Mountains National Park: Mountain Nyala, Ethiopian wolf, Spotted hyena.
     - Kafta Sheraro National Park: African elephant, Ground squirrel, Crested porcupine.
     - Nechisar National Park: Plains zebra, Greater kudu, Dik-dik.
     - Yangudi Rassa National Park: Beisa oryx, Grevy's zebra, Abyssinian mohr.
   • Conditional Sentence Constructions in Park Contexts:
     - Type I (Real Future Possibility):
       "If tourists visit the Semien Mountains, they will see the endemic Walia ibex."
     - Type II (Unreal / Hypothetical Present):
       "If I lived near the Bale Mountains, I would photograph the rare Ethiopian wolf."
     - Type III (Counterfactual / Impossible Past):
       "If park rangers had not protected the habitat, the endemic species would have disappeared."

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Endemic Species Definition: Organisms naturally restricted to one specific geographical location (e.g., Walia ibex to Ethiopia; Lemurs to Madagascar).
• Denotation vs. Connotation: Denotation is the dictionary definition; Connotation is the emotional/cultural aura (e.g., "economical" = positive; "stingy" = negative; both denote spending little money).
• National Park Wildlife Matching: Walia ibex -> Semien Mountains; Mountain Nyala & Ethiopian wolf -> Bale Mountains; Elephants -> Kafta Sheraro.
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 87
    ),
    ShortNote(
        id = "eng-u5",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 5",
        title = "Population: Demographics, Pyramids, Collocations & Passive Voice",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Unit 5 (FLEn 1011) examines Population studies and demographics. It covers the geographic mechanics of population density, the interpretation of age-sex population pyramids across demographic transition stages, lexical collocations, and the syntactic mastery and academic utility of Active versus Passive voice constructions.

🔍 DETAILED BREAKDOWN
1. Listening: Population Density & Spatial Demographics:
   • Core Demographic Definitions:
     - Population: The total count of human individuals or inhabitants residing within a defined geographical territory or administrative boundary.
     - Dense / Crowded: Characterized by people or structural objects packed closely together.
     - Population Density: The fundamental geographic ratio calculated as:
       Population Density = Total Population / Total Land Area (expressed as persons per km² or mile²).
   • Multi-Level Spatial Scales: Density can be analyzed across a city, district, province/region, sovereign country, or the entire planet.
   • Global Habitability: More than 50% of the Earth's continental landmass is classified as inhospitable (unfavorable or hostile to human permanent settlement due to hyper-arid deserts, rugged mountain ranges, sub-polar ice caps, or dense swamps).

2. Reading: Population Pyramids & Age-Sex Structure:
   • Socio-Economic Significance of Age Structure:
     - Determines the active labor force supply relative to dependent age groups.
     - A disproportionate ratio of children (0–14) or elderly (65+) creates a high Dependency Ratio, causing labor shortages in agriculture and manufacturing, necessitating food imports, and increasing demands on public schools, teachers, and geriatric healthcare.
   • Anatomy of a Population Pyramid (Age-Sex Graph):
     - Specialized bilateral horizontal bar chart displaying demographic distribution.
     - Vertical Y-Axis: Represents five-year age cohorts (e.g., 0–4 at the base, progressing upward to 80+ at the apex).
     - Horizontal X-Axis / Central Divider: The center represents 0% or zero population.
       * Males are plotted from the center outward to the LEFT.
       * Females are plotted from the center outward to the RIGHT.
     - Historical Origin: Termed a "pyramid" because in the 19th century, nearly all nations had expansive broad bases (high birth rates) tapering to narrow apexes (high death rates).
   • Four Typologies of Population Pyramids across Demographic Transition:
     1. Stage 1 (Expansive / Traditional):
        - Shape: Wide base with steep, rapidly sloping triangular sides.
        - Dynamics: High birth rates combined with high infant mortality and death rates.
        - Context: Pre-industrial societies, historic populations, and fragile developing regions.
     2. Stage 2 (Expansive / Rapid Growth):
        - Shape: Very broad base, narrow apex, but with outward-curving sides.
        - Dynamics: Persistently high birth rates with rapidly falling/declining death rates due to sanitation and basic healthcare.
        - Context: Rapidly growing developing nations in sub-Saharan Africa.
     3. Stage 3 (Stationary / Beehive Shape):
        - Shape: Upright cylindrical or "beehive" shape with rounded apex.
        - Dynamics: Low birth rates balanced with low death rates; high proportion of middle-aged and elderly individuals.
        - Context: Developed nations (e.g., England, Wales, France).
     4. Stage 4 (Constrictive / Inverted):
        - Shape: Tapered, narrow base that is narrower than the middle and upper tiers.
        - Dynamics: Rapidly falling birth rates below replacement level (sub-replacement fertility) and low mortality; shrinking workforce.
        - Context: Highly aging, shrinking societies (e.g., Japan, Italy, Germany).

3. Vocabulary: Collocations in Academic English:
   • Definition: A conventional, fixed, or highly predictable pairing of two or more words that naturally co-occur through customary linguistic usage.
   • Key Collocations with "Population":
     - Population density, population pyramid, population growth, aging population, urban population, sparse population.
   • High-Frequency Verb + Noun Collocations:
     - Have: Have a bath, have a break, have an operation, have an impact.
     - Do: Do business, do homework, do research, do an exercise, do a favor.
     - Make: Make an effort, make a noise, make a decision, make a mistake, make a promise.
     - Take: Take notes, take time, take a break, take a risk, take medicine.
     - Break: Break a habit, break a record, break a promise, break the law.
     - Catch: Catch a disease, catch a cold, catch a bus, catch sight of.
     - Pay: Pay attention, pay a visit, pay a compliment, pay a fine.
     - Keep: Keep a promise, keep calm, keep a routine, keep a secret.

4. Grammar Focus: Active vs. Passive Voice:
   • Conceptual Mechanics:
     - Active Voice: The grammatical subject (agent) actively performs the verb's action:
       Subject (Agent) + Transitive Verb + Object (Patient).
       * Example: "Demographers have developed a special bar graph."
     - Passive Voice: The grammatical subject (patient) receives the action:
       Subject (Patient) + Appropriate form of 'to be' + Past Participle (V3) + [by Agent].
       * Example: "A special bar graph has been developed by demographers."
   • Formation Across Core Tenses:
     - Simple Present: am / is / are + V3 (e.g., "Census data is collected every decade").
     - Simple Past: was / were + V3 (e.g., "The pyramid was drafted yesterday").
     - Present Perfect: have / has been + V3 (e.g., "New population policies have been enacted").
   • When to Use the Passive Voice in Academic Discourse:
     1. When the agent is unknown, obvious, or irrelevant (e.g., "The survey was conducted across 500 households").
     2. To maintain an objective, formal academic register that highlights findings, procedures, and empirical results rather than the personal identity of the researcher.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Population Pyramid Rules: Males are ALWAYS plotted to the LEFT; Females to the RIGHT.
• Four Pyramid Shapes: Wide triangle = Stage 1 (high birth/death); Inward curve = Stage 2 (high birth, falling death); Beehive = Stage 3 (low birth/death); Narrow base = Stage 4 (declining, aging).
• Collocation Traps: We say "DO homework" and "DO business" (NEVER "make homework"); but "MAKE an effort" and "MAKE a decision" (NEVER "do an effort").
• Passive Formula: Always requires a form of 'to be' + Past Participle (V3).
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 90
    ),
    ShortNote(
        id = "geo-u1",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 1",
        title = "Introduction to Geography of Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Geography is the scientific study of the spatial and temporal variations of physical, biological, and human phenomena across the Earth's surface. This unit introduces geographic scopes, approaches, and fundamental themes, the astronomical and relative location of Ethiopia and the Horn, the advantages/disadvantages of national territorial size, mathematical indices of compactness, and cartographic map-reading skills.

🔍 DETAILED BREAKDOWN
1. Meaning, Scope, Approaches & Themes of Geography:
   • Definition: Scientific analysis of spatial and temporal distributions of natural and cultural phenomena, their causal relationships, and dynamism over Earth's surface.
   • Scope: The Earth's surface—the habitable interface where Atmosphere (air), Lithosphere (rock/crust), Hydrosphere (water bodies), and Biosphere (living organisms) interact.
   • Major Approaches:
     - Human–Physical Continuum: Physical Geography (natural processes) vs. Human Geography (cultural, socioeconomic, and demographic patterns).
     - Topical (Systematic) vs. Regional: Topical studies individual phenomena globally (e.g., climatology, geomorphology); Regional examines all intersecting phenomena within a specific bounded geographical unit.
   • The 5 Fundamental Themes of Geography:
     1. Location: Absolute (mathematical coordinates: latitudes & longitudes) vs. Relative (position with respect to adjacent landmasses, water bodies, or neighboring states).
     2. Place: Physical and human character; toponym (name), site (internal physical attributes: terrain, soils, drainage), and situation (regional/socioeconomic context).
     3. Human-Environment Interaction: Dependency (relying on natural resources), Adaptation (modifying lifestyles/clothing/housing to fit environment), and Modification (altering nature via dams, agriculture, urbanization).
     4. Movement: Spatial mobility of people (migration), goods/commodities (trade), and ideas/innovations (diffusion).
     5. Region: Distinctive spatial units; Formal Region (homogeneous by physical or cultural traits like climate or language) vs. Functional/Nodal Region (organized around focal hubs and transport/communication networks).

2. Location of Ethiopia and the Horn of Africa:
   • The Horn of Africa: Easternmost projection of Africa separating the Gulf of Aden from the Indian Ocean. Comprises 4 member states: Djibouti, Eritrea, Ethiopia, and Somalia.
     - Ethiopia is the LARGEST country in the Horn; Djibouti is the SMALLEST.
   • Astronomical (Absolute) Location of Ethiopia:
     - Latitudinal Extent: 3°N (Moyale) to 15°N (Bademe in Tigray) => 12° North-South span (~1,577 km).
     - Longitudinal Extent: 33°E (Akobo in west) to 48°E (tip of Ogaden in east) => 15° East-West span (~1,639 km).
     - East-West span (15°) exceeds North-South span (12°).
     - 15° longitudinal span creates a 1-hour solar time difference between western and eastern borders (a single UTC+3 / GMT+3 time zone is observed nationwide).
   • Relative (Vicinal) Location:
     - Bordering Countries: Eritrea (North & NE), Sudan (West & NW), South Sudan (SW), Kenya (South), Djibouti (East), Somalia (East & SE).
     - Broader Context: Southwest of the Arabian Peninsula, south of Europe, upper catchment of the Nile Basin, and adjacent to the Red Sea maritime corridor.
   • Implications of Ethiopia's Location:
     - Climatic: Wholly within the tropics (3°N - 15°N); high solar angles year-round, but tropical heat is strongly modified and cooled by altitudinal elevation.
     - Socio-Cultural: Proximity to Middle East enabled early introduction of Judaism, Christianity, and Islam; shared cultural/linguistic ties across the Horn.
     - Geopolitical: Overlooks the critical Red Sea & Bab-el-Mandeb trade chokepoint connecting the Indian Ocean to the Mediterranean; historically exposed to external invasions while fiercely preserving sovereignty.

3. Size of Ethiopia (Advantages & Disadvantages):
   • Total Area: Approx. 1,106,000 km² (8th largest in Africa, 25th in the world). Inland waters account for ~0.7% of surface area.
   • Advantages of Large Territorial Size:
     - Encompasses diverse agro-ecological zones and varied climates.
     - Abundantly endowed with diverse natural, mineral, and water resources.
     - Extensive arable land capable of sustaining a large population.
     - Provides deep strategic territorial depth against external military invasions.
   • Disadvantages of Large Territorial Size:
     - Demands massive capital expenditure for road, railway, and utility infrastructure.
     - Requires large military and security expenditures to patrol 5,260 km of borders.
     - Poses administrative, governance, and public service delivery challenges in peripheral areas.
     - Hampers quick national socioeconomic integration; cross-border illicit trade is difficult to monitor.

4. Shape of Countries & Indices of Compactness:
   • The 5 Basic Country Shapes:
     - Compact: Center-to-boundary distance is relatively uniform; geometrically ideal for defense, communication, and internal cohesion.
     - Fragmented: Discontinuous pieces separated by water or foreign territory (e.g., archipelagos).
     - Elongated: Long and narrow (e.g., Chile, Norway).
     - Perforated: Territory completely encloses another sovereign state (e.g., South Africa enclosing Lesotho).
     - Protruded: Possesses a prominent panhandle or corridor extending outward (e.g., Eritrea, Caprivi Strip).
   • Mathematical Indices of Compactness (Measuring deviation from a circle):
     - Area–Boundary (A/B) Ratio = Area / Boundary Length. Higher values indicate greater compactness.
     - Boundary–Circumference (B/C) Ratio = Actual boundary perimeter / Circumference of a circle with the same area. Closer to 1.0 indicates higher compactness.
     - Area–Circumference (A/C) Ratio = Actual area / Circumference of the smallest circumscribing circle. Higher values indicate higher compactness.
     - Area–Area (A/A') Ratio = Actual country area / Area of the smallest circumscribing circle. Values closer to 1.0 denote maximum compactness.
   • Horn of Africa Compactness Values (Bekure's Measurements):
     - Ethiopia: Area = 1,106,000 km² | Boundary = 5,260 km | A/B = 210.27 | B/C = 1.41 | A/C = 296.61 (Relatively compact shape).
     - Kenya: Area = 582,644 km² | Boundary = 3,600 km | A/B = 161.85 | B/C = 1.33 | A/C = 215.28.
     - Somalia: Area = 637,657 km² | Boundary = 5,100 km | A/B = 125.03 | B/C = 1.80 | A/C = 225.22.
     - Eritrea: Area = 117,400 km² | Boundary = 2,420 km | A/B = 48.51 | B/C = 1.99 | A/C = 96.83.
     - Djibouti: Area = 22,000 km² | Boundary = 820 km | A/B = 26.83 | B/C = 1.56 | A/C = 41.83.

5. Basic Skills of Map Reading:
   • Map Definition: A two-dimensional, scaled, conventional representation of part or all of the Earth's surface onto a flat plane.
   • Map Classifications:
     - Topographical Maps: General-purpose maps depicting both natural physical relief (contours, rivers) and cultural human-made features (roads, settlements).
     - Thematic (Statistical) Maps: Specialized maps displaying the spatial distribution of single phenomena (e.g., rainfall, temperature, population density, soil types).
   • Essential Marginal Elements (Map Anatomy):
     - Title (subject and region), Key/Legend (symbols and color codes), North Arrow/Orientation (cardinal directions), Margin/Border (framing limits), Date of Compilation (data currency).
     - Scale Formats: Representative Fraction (e.g., 1:50,000), Verbal/Statement Scale (1 cm to 500 m), and Graphic/Linear Scale.
   • Principles of Map Reading:
     - Symbol Visualization (translating 2D symbols into landscape topography).
     - Orientation & Cardinal Direction (finding geographic North).
     - Alphanumeric Grid References (pinpointing coordinates on Easting and Northing grids).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Longitude vs. Latitude Dimensions: Ethiopia's East-West longitudinal span (15°, from 33°E to 48°E) is wider than its North-South latitudinal span (12°, from 3°N to 15°N).
• Time Difference: The 15° longitudinal span generates an exact 1-hour solar time difference between the westernmost point (Akobo) and easternmost point (Ogaden border).
• Country Shape Classification: Ethiopia has a COMPACT shape (not elongated, fragmented, or perforated). Its A/B ratio (210.27) is the highest in the Horn.
• Boundary-Circumference Index: In B/C ratio, the closer the value is to 1.0, the closer the country's shape is to a perfect circle (Ethiopia = 1.41, Kenya = 1.33).
• Map Scales: A Representative Fraction (RF) of 1:25,000 is a LARGER scale than 1:250,000 because its fraction (1/25,000) is mathematically larger and displays greater local detail over a smaller area.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 83
    ),
    ShortNote(
        id = "geo-u2",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 2",
        title = "The Geology of Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Geology investigates the Earth's origin, structural composition, and internal/external dynamic processes. Ethiopia's diverse topography and mineral distribution are shaped by four major geological eras: Precambrian crystalline basement formation, Paleozoic peneplanation, Mesozoic marine transgression/regression cycles, and Cenozoic epeirogenic uplift, rift valley formation, and fissure volcanism.

🔍 DETAILED BREAKDOWN
1. Continental Drift & Geological Forces:
   • Continental Drift Theory (Alfred Wegener): Suggests all continents formed a single supercontinent (Pangaea), which split into Gondwanaland (including Africa) and Laurasia before breaking into modern continents.
   • Wegener's Supporting Evidence:
     - Matching continental coastlines (e.g., South America and Africa jigsaw fit).
     - Continuous geological structures and mountain belts across ocean basins.
     - Identical fossil belts across widely separated landmasses (e.g., Glossopteris flora, Mesosaurus).
     - Paleoclimatic evidence (200-million-year-old glacial striations in modern tropical landmasses).
   • Geomorphic Processes:
     - Endogenic (Internal) Forces: Powered by Earth's thermal energy; volcanism and tectonic processes (folding, faulting, orogenesis, epeirogenesis) that construct primary landforms (plateaus, volcanic massifs, rift grabens).
     - Exogenic (External) Forces: Surface processes (weathering, mass wasting, erosion, deposition) driven by solar energy and gravity that continuously carve, erode, and lower structural landforms.

2. Geological Time Scale & Age-Dating Techniques:
   • Hierarchy of Time Units: Eon > Era > Period > Epoch.
   • The Four Major Eras:
     - Precambrian (4.5 billion – 600 mya): Spans 5/6th of Earth's history; emergence of earliest rocks and primitive marine life.
     - Paleozoic (600 – 225 mya): Invertebrates, earliest fish, terrestrial vegetation, and coal-forming swamps; concludes with the Permian mass extinction.
     - Mesozoic (225 – 70 mya): Era of reptiles (dinosaurs), breakup of Pangaea, marine sedimentation.
     - Cenozoic (70 mya – Present): Era of mammals; Alpine-Himalayan orogeny, modern mountain/rift systems, hominid evolution.
   • Age-Dating Methods:
     - Relative Dating: Comparative age determination using geological principles:
       * Law of Superposition: In undisturbed sedimentary sequences, bottom strata are oldest and top layers are youngest.
       * Fossil Correlation: Uses unique index fossils to synchronize strata across geographic regions.
     - Absolute (Radiometric) Dating: Calculates exact calendar ages based on constant half-lives of radioactive parent isotopes:
       * Carbon-14 Dating (¹⁴C): Half-life = 5,730 years; dates organic matter up to ~50,000–60,000 years.
       * Potassium-Argon (⁴⁰K–⁴⁰Ar): Dates igneous minerals (feldspar, mica, hornblende); reheating above 125°C causes argon gas escape, resetting the radiometric clock.

3. Geological Evolution and Landform Genesis in Ethiopia:
   • Precambrian Era (4.5 billion – 600 mya):
     - Events: Orogenesis, intense folding, intrusive magmatism, and high-grade metamorphism forming the crystalline Basement Complex (schists, gneisses, granites).
     - Subsequent prolonged denudation eroded mountains into a flat peneplain.
     - Surface Outcrops (covers ~25% of Ethiopia): Western lowlands (Gambella, Benishangul-Gumuz, western Wellega/Gojjam), northern Tigray, southern lowlands (Guji, Borena, southern Omo), and deep river gorge cuts (Abay Gorge).
   • Paleozoic Era (600 – 225 mya):
     - Dominated by continuous denudation and erosion; land leveled to an undulating peneplain punctuated by isolated inselbergs.
     - Sediments were transported out of Ethiopia into foreign marine basins.
     - Result: Rocks from the Paleozoic Era are virtually ABSENT in Ethiopia due to the absence of marine deposition.
   • Mesozoic Era (225 – 70 mya):
     - Alternating crustal subsidence and uplift (epeirogenesis) and southeastward tilting produced marine transgression and regression:
       1. Marine Transgression (Late Triassic – Jurassic): Indian Ocean flooded landmass from Southeast (Somalia/Ogaden) toward Northwest.
          * Adigrat Sandstone: Basal shallow-water sandstone deposited directly over Precambrian basement.
          * Hintalo Limestone: Formed as sea deepened; rich in calcareous marine deposits and gypsum (hydrocarbon source rock).
       2. Marine Regression (Upper Jurassic – Cretaceous): Crustal uplift forced the sea to retreat southeastward.
          * Upper Sandstone: Coastal silts, sands, and clays laid down atop limestone during regression.
     - Spatial Thickness & Age Variations:
       * Adigrat Sandstone is older and thicker in the Southeast (early flood) and younger and thinner toward the Northwest.
       * Upper Sandstone is older and thinner in the Northwest and younger and thicker in the Southeast (last marine retreat).
     - Mesozoic rocks cover ~25% of Ethiopia (dominant in Ogaden, central Tigray, and Abay/Wabe Shebelle gorges).
   • Cenozoic Era (70 mya – Present):
     - Most dynamic era; covers ~50% of the Ethiopian landmass. Governed by 3 major structural events:
       1. Arabo-Ethiopian Swell Uplift & Trap Series Volcanism (Tertiary): Massive dome uplift (>2,000 m) accompanied by extensive crustal fracturing and fissure flood basalts (Trap Series) up to 1,000 m thick, capping Mesozoic sediments and creating high plateaus and shield volcanoes (Simien, Bale).
       2. Great Rift Valley Formation: Tensional forces caused normal faulting and graben subsidence, separating African and Arabian plates, dividing plateaus into Western and Southeastern Highlands, and forming the 1,700-km-long Ethiopian Rift system (widest at the Afar Depression triple junction, down to -125 m at Kobar Sink/Dallol).
       3. Quaternary Volcanism & Deposition (Aden Series): Recent volcanic cones (Erta Ale, Fantale, Dubi), crater lakes, hot springs, and Pleistocene pluvial lacustrine/fluvial sediment deposits.

4. Rock and Mineral Resources & Greenstone Belts:
   • Stratigraphic Mineral Associations:
     - Precambrian: Primary metallic ores (Gold, Platinum, Tantalum, Iron) and industrial marble.
     - Mesozoic: Limestone, gypsum, anhydrite, and prospective oil/natural gas reservoirs.
     - Cenozoic: Coal (lignite), geothermal steam, salts/potash, soda ash, pumice, scoria, and basalt building stone.
   • Notable Mineral Deposits & Localities:
     - Gold (Au): Primary lode veins (Lega-dembi, Sakaro in Adola); alluvial/placer deposits (Benishangul-Gumuz, Akobo in Gambella, western Wellega).
     - Platinum (Pt): Yubdo in western Wellega (Ethiopia's primary commercial platinum mine).
     - Tantalum (Ta): Kenticha open-pit pegmatite mine in Adola (>17,000 metric tons of tantalite-columbite ore).
     - Coal (Lignite): Interbedded in Tertiary volcano-sedimentary units at Chilga (Gondar), Nedjo (Wellega), and Ankober.
     - Potash & Halite: Vast evaporite salt deposits in the Danakil (Dallol) Depression.
     - Gemstones: Precious Opal (Wadla & Dalanta in North Wollo), emeralds, peridots, and sapphires.
     - Construction Stones: Mesozoic limestone used for cement factories (Muger, Messebo, Dire Dawa, Dejen).
   • The 3 Major Precambrian Greenstone Belts:
     1. Western & South-Western Greenstone Belt: Gold (Dul, Tulu-Kape, Akobo), Yubdo platinum, base metals, and Bikilal iron ore.
     2. Southern (Adola) Greenstone Belt: Primary lode gold (Lega-dembi), world-class tantalum (Kenticha), and nickel.
     3. Northern (Tigray) Greenstone Belt: Primary gold (Terakemti, Adi-Zeresenay) and copper-lead base metals.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Mesozoic Stratigraphic Sandwich: Base = Adigrat Sandstone, Middle = Hintalo Limestone, Top = Upper Sandstone.
• Spatial Thickness Inversion: Adigrat Sandstone is THICKEST in the SE (Ogaden) and THINNEST in the NW; Upper Sandstone is THICKEST in the SE and THINNEST in the NW (due to regression direction).
• Absence of Paleozoic Rocks: Paleozoic rocks are virtually nonexistent in Ethiopia because the land was an elevated peneplain experiencing denudation, with zero marine deposition.
• Era Landmass Coverage: Cenozoic covers 50%, Precambrian covers 25%, and Mesozoic covers 25% of Ethiopia.
• Radiometric Reset Hazard: In ⁴⁰K–⁴⁰Ar dating, if volcanic rocks are reheated above 125°C, radiogenic argon gas leaks out, resetting the clock to the thermal event rather than the initial rock crystallization.
• Tantalum Capital: The Kenticha mine in the Adola greenstone belt holds world-class reserves of high-grade tantalite.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 89
    ),
    ShortNote(
        id = "geo-u3",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 3",
        title = "The Topography of Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia's contemporary topography is the product of Cenozoic tectonic events: epeirogenic uplift of the Arabo-Ethiopian swell, massive Trappean flood basalt volcanism, tectonic rifting, and profound river gorge incision. Renowned as the "Roof of Africa" and "Water Tower of East Africa," the country displays extreme physiographic contrast between high volcanic plateaus and extensive peripheral lowlands.

🔍 DETAILED BREAKDOWN
1. General Characteristics of Ethiopian Physiography:
   • Altitudinal Extremes:
     - Lowest Point: Kobar Sink (Dallol / Danakil Depression) at 125 meters below sea level (-125 m).
     - Highest Point: Mount Ras Dashen at 4,620 meters above sea level (4th highest peak in Africa).
   • The "Roof of East Africa": Ethiopia possesses the largest expanse of continuous elevated land in Africa:
     - >50% of national territory lies above 1,000 meters.
     - ~44% lies above 1,500 meters (comfort zone).
     - >22% exceeds 2,000 meters.
   • Highlands vs. Lowlands Contrast (Demarcated at the 1,000-Meter Contour):
     - Highlands (>1,000 m; ~56% of area): Moderate-to-high rainfall (>600 mm/year), cool mean annual temperatures (<20°C), absence of tropical disease vectors (malaria-free), high population densities, sedentary mixed agriculture (crops and livestock).
     - Lowlands (<1,000 m; ~44% of area): Low, erratic rainfall (<500 mm/year), high temperatures, high disease prevalence (malaria, trypanosomiasis), sparse pastoral and nomadic populations, livestock herding and river valley irrigation.

2. The Western Highlands and Lowlands (44% of Ethiopia):
   • Subdivided into Western Highlands (76.3%) and Western Lowlands (23.7%).
   • A. The Western Highlands:
     - Tigray Plateau (13% of unit; 1,000–2,000 m): Rugged, denuded granite hills, flat-topped ambas (Debre Damo). High peaks: Mt. Tsibet (3,988 m), Mt. Ambalage (3,291 m), Mt. Assimba (3,248 m).
     - North Central Massifs: Largest and second highest subdivision (58% >2,000 m), bounded by deep Abay and Tekeze canyons; contains Lake Tana basin and 19 of Ethiopia's 26 peaks above 4,000 m:
       * Simen Mountains: Mt. Ras Dashen (4,620 m), Mt. Weynobar (4,462 m), Mt. Kidis Yared (4,453 m), Mt. Bwahit (4,437 m).
       * Other systems: Mt. Guna (4,231 m in Debre Tabor), Abune Yoseph (4,260 m in Lasta), Mt. Birhan (4,154 m in Choke).
       * Yeju-Wadla Delanta Land Bridge: Narrow strategic high-altitude ridge connecting northern massifs.
     - Shewa Plateau (Central Highlands): 11% of unit, but nearly 75% exceeds 2,000 m. Central drainage divide feeding Abay, Awash, and Omo basins. Highest peaks: Mt. Abuye-Meda (4,000 m in North Shewa), Mt. Guraghe (3,721 m).
     - Southwestern Highlands (22.7% of unit): Wettest region of Ethiopia; deeply dissected by perennial rivers (Baro, Akobo, Didessa, Omo); cradle of Arabica coffee and rich linguistic diversity. Highest peak: Mt. Guge (4,200 m in Gamo-Gofa).
   • B. The Western Lowlands (11% of unit; 500–1,000 m):
     - Along Sudan and South Sudan borders: Tekeze Lowland, Abay-Dinder Lowland, Baro Lowland (wettest lowland with fertile alluvial plains for commercial mechanized farming), and Ghibe/Omo Lowland.
     - Major agricultural/border towns: Humera, Metema, Kurmuk, Gambella.

3. The Southeastern Highlands and Lowlands (37% of Ethiopia):
   • Highlands account for 46%, Lowlands account for 54%. Escarpment drops sharply into the Rift Valley, while terrain slopes gently southeast toward the Indian Ocean.
   • A. The Southeastern Highlands:
     - Arsi-Bale-Sidama Highlands (62% of SE Highlands):
       * Arsi Highlands: Dissected basaltic volcanic cones: Mt. Kaka (4,180 m), Mt. Bada (4,139 m), Mt. Chilalo (4,036 m).
       * Bale Highlands: Afro-alpine Sanetti Plateau and glaciated peaks: Tulu-Demtu (4,377 m, highest in SE Ethiopia) and Mt. Batu (4,307 m).
       * Sidama Highlands: High, fertile Jemjem plateau (vital for coffee and enset cultivation).
       * Sof Omar Cave: Spectacular subterranean limestone cave carved by the Weyb River (Ghenale tributary) through Mesozoic sediments.
     - Hararghe Plateau (38% of SE Highlands): Narrow horst rising sharply from the Rift floor and extending to Jigjiga; basalt cap largely stripped away exposing Mesozoic limestone/sandstone. Highest peak: Mt. Gara-Muleta (3,381 m).
   • B. The Southeastern Lowlands (~20% of Ethiopia):
     - Slopes gently toward Somalia and Indian Ocean; incorporates Wabishebelle Plain (60%) and Ghenale Plain (40%) across Ogaden, Elkere, and Borena.
     - Characterized by dry thorn-bush steppe, inselbergs, shallow valleys, pastoral livestock rearing, and hydrocarbon (natural gas) potential.

4. The Rift Valley Physiographic Division (18% of Ethiopia):
   • A 1,700-km structural depression from Afar southwest to Chew Bahir; funnel-shaped trough opening wide in the north and narrowing southward.
   • Highly unstable tectonic zone with active volcanoes, fumaroles, crater lakes, and seismic tremors.
   • Three Distinct Physiographic Sections:
     1. Afar Triangle (Northern Rift, 54% of Rift area): Lowest and widest (200–300 km) section; includes Kobar Sink (-125 m bsl) and Dallol. Triple junction of Red Sea, Gulf of Aden, and African rifts. Hostile arid climate (>50°C), active shield volcanoes (Erta Ale), salt lakes (Lake Asale, Afrera), and Awash River termination in Lake Abe.
     2. Main Ethiopian Rift (Central Rift): Elevated trench (50–80 km wide, 1,000–2,000 m elevation) between Awash basin and Lake Chamo. Features young cinder cones (Mt. Fentale, Boseti-guda) and scenic Rift Valley lakes (Ziway, Langano, Abijata, Shalla, Hawassa, Abaya, Chamo).
     3. Chew Bahir Rift (Southern Rift): Smallest, marshy tectonic depression fed by Woito and Segen rivers, separated from the Central Rift by the Konso crystalline basement horst.

5. Impacts of Relief on Biophysical & Human Environments:
   • Agriculture: Rugged slopes dictate fragmented small farm plots and hinder mechanization; altitude determines crop belts (barley/wheat/pulses in highlands vs. sorghum/maize/cotton in lowlands) and livestock adaptation (sheep/cattle vs. camels/goats).
   • Settlement: Highlands (>1,500 m) house dense sedentary farming communities; lowlands feature sparse, mobile pastoralism.
   • Transport: Deep river canyons and steep escarpments severely hinder road/rail networks, elevating construction costs and isolating communities.
   • Hydroelectric Power (HEP): Exceptional hydropower potential due to steep elevation drops, narrow river gorges, and waterfalls.
   • Socio-Cultural Diversity: Mountain barriers fostered regional linguistic diversity and cultural identities (Degegnas vs. Kollelgas).
   • Climate & Ecology: Orographic rain and adiabatic lapse rates create moist, temperate highland "climatic islands" amidst hot arid lowlands.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Highest vs. Lowest Elevation: Ras Dashen (4,620 m) in Simen Mountains is the highest point; Kobar Sink (-125 m) in Afar is the lowest point.
• Highest Peaks by Sub-Unit: Western Highlands = Ras Dashen (4,620 m); Southeastern Highlands = Tulu-Demtu (4,377 m) in Bale; Hararghe Plateau = Gara-Muleta (3,381 m); Gamo Highlands = Mt. Guge (4,200 m).
• Highland-Lowland Demarcation Line: The 1,000-meter contour line divides Ethiopia into Highlands (56%) and Lowlands (44%).
• Shewa Plateau Altitude Distinction: Although only 11% of Western Highlands by area, nearly three-fourths (75%) of the Shewa Plateau lies above 2,000 m.
• Sof Omar Cave Formation: Natural subterranean cavern formed by the Weyb River dissolving Mesozoic marine limestone near the Bale Mountains.
• Afar Triple Junction: Tectonic junction where the Red Sea Rift, Gulf of Aden Rift, and East African Rift meet.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 94
    ),
    ShortNote(
        id = "geo-u4",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 4",
        title = "Drainage Systems and Water Resources of Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia is renowned as the "Water Tower of Eastern Africa" owing to its elevated topography, high orographic rainfall, and radiating outward-tilted plateaus. The country produces an estimated 124.4 billion cubic meters (BCM) of surface runoff annually, distributed across three major drainage systems (Western, Southeastern, and Rift Valley), numerous tectonic and volcanic lakes, and significant groundwater and hydroelectric potential.

🔍 DETAILED BREAKDOWN
1. Global Hydrology & Structural Controls:
   • Global Water Context: ~71% of Earth's surface is water; 97.5% is ocean saline water, only 2.5% is freshwater (68.7% locked in glaciers/ice, 30.1% groundwater, 0.8% permafrost, and 0.4% in lakes, rivers, and atmosphere).
   • Ethiopian Water Wealth: Inland water bodies cover ~0.7% of national surface area; annual surface runoff exceeds 124.4 BCM.
   • Structural Controls of Drainage:
     - Outward tilt of Western plateau slopes drainage westward toward the Nile.
     - Outward tilt of Southeastern plateau slopes drainage southeastward toward the Indian Ocean.
     - Inward-facing fault escarpments of the Great Rift Valley trap inland drainage (endorheic basins).
     - Faults, joints, and tectonic grabens direct specific river courses.

2. The Three Principal Drainage Systems:
   • 1. The Western Drainage System:
     - Largest system: Drains 40% of national land area and discharges 60% of total annual runoff (~54+ BCM).
     - Major rivers flow westward into the Nile and Mediterranean Sea (except Ghibe/Omo which flows south).
     - Abay River Basin: Largest volumetric discharge and coverage (199,812 km² across Amhara, Oromia, and Benishangul-Gumuz); carries 65% of the western system flow. Originates from Lake Tana (ultimate source at Gilgel Abay / Sekela in Choke Mountains) and flows 1,450 km to join the White Nile in Khartoum. Major tributaries: Dabus, Dedessa, Fincha, Guder, Muger, Jema, Beshilo.
     - Tekeze River Basin: Catchment of 87,733 km²; annual runoff of 8.2 BCM (12% of western flow). Tributaries: Goang and Angereb; known as the Atbara River in Sudan.
     - Baro-Akobo River Basin: Catchment of 75,912 km²; drains wet southwestern highlands, discharging 23.23 BCM (17% of western flow). Joins Akobo to form the Sobat River in South Sudan. Lower reach is the ONLY navigable river stretch in Ethiopia.
     - Ghibe/Omo River Basin: Catchment of 79,000 km²; annual runoff of 16.6 BCM. Fed by the Gojeb tributary. Endorheic system emptying into Lake Turkana.
   • 2. The Southeastern Drainage System:
     - Drains 25% of Ethiopia's total annual runoff across Arsi, Bale, Sidama, and Hararghe toward the Indian Ocean.
     - Wabishebelle River Basin: Largest catchment area in Ethiopia (202,697 km²) and longest river, but discharges only ~3.4 BCM annually. Tributaries: Ramis, Erer, Daketa, Fafan. Disappears in coastal sands of southern Somalia before reaching the sea.
     - Ghenale-Dawa River Basin: Catchment of 171,042 km²; discharges 5.8–6.0 BCM. Tributaries: Dawa, Weyb, Welmel, Mena. Joins at Somali border to form the Juba River and successfully discharges into the Indian Ocean.
   • 3. The Rift Valley (Inland) Drainage System:
     - Closed endorheic drainage with no ocean outlet, high evaporation, and low rainfall.
     - Awash River Basin: Only major river basin in the Rift Valley (114,123 km², 1,250 km long, 4.9 BCM). Rises in Shewan plateau, flows northeast, fed by Akaki, Kesem, Borkena, Mile. Most economically utilized river (Wonji, Metehara, Tendaho irrigation and Koka HEP). Terminates in Lake Abe inland saline delta.
     - Southern Lakes Basin: Chains of interconnected lakes: Meki & Katar => Lake Ziway => Bulbula River => Lake Abijata; Lake Langano => Horocolo River => Lake Abijata; Bilate River => Lake Abaya; Segen River => Chew Bahir.

3. General Characteristics of Ethiopian Rivers:
   • Highland Headwaters: Originate in high plateaus (>1,500 m) with steep gradients.
   • Trans-boundary Flow: Major rivers flow across international borders into Sudan, South Sudan, Somalia, and Kenya.
   • Extreme Seasonal Fluctuations: Runoff mirrors rainfall (raging torrents/floods in Kiremt, trickles in dry Bega).
   • Youthful River Profiles: Marked by rapids, cataracts, and waterfalls (e.g., Tis Issat Falls).
   • Deep Gorges & Canyons: Profound vertical incision forms natural physical barriers and administrative boundaries.

4. Ethiopian Lakes (Morphometry & Origin):
   • Lake Classifications (All Quaternary Cenozoic Origin):
     - Highland Lava-Dammed / Tectonic: Lake Tana (largest in Ethiopia: 3,600 km², max depth 9 m; source of Blue Nile); Lake Ashenge (Tigray tectonic basin, 20 km², 25 m deep).
     - Rift Valley Tectonic Lakes: Lake Abaya (largest Rift lake: 1,162 km², 13.1 m deep); Lake Chamo (551 km², 13 m deep); Lake Ziway (shallow freshwater: 442 km², 8.95 m deep); Lake Shalla (caldera sink: 409 km², 266 m deep — DEEPEST lake in Ethiopia); Lake Abijata (alkaline soda lake, 205 km²).
     - Volcanic Explosion Crater Lakes: Bishoftu lakes, Wonchi (near Ambo), Hayk (near Dessie, 5 km², 23 m deep), Mt. Zikwala summit crater.
     - Artificial Reservoirs: Koka (205 km²), Fincha, Melka Wakena, Gilgel Gibe, Tekeze.

5. Water Resource Potential & Economic Development:
   • Hydroelectric Power (HEP): 45,000 MW exploitable potential. First plant built in 1932 (Aba Samuel on Akaki). Major plants include Gibe III (1,870 MW), Tekeze, Tana Beles, Fincha. Grand Ethiopian Renaissance Dam (GERD) on Abay is designed for 6,400 (or 5,150) MW. Challenges: seasonal flow variability, reservoir siltation from highland soil erosion.
   • Irrigation & Inland Navigation: 5.3 million hectares irrigable land. Over 60% of modern irrigation is concentrated in Awash basin (sugar cane, cotton). Baro River is the ONLY navigable river during high water; Lakes Tana and Abaya support commercial water transport.
   • Fisheries & Environmental Threats: National fish potential ~30,000–45,000 tons/year. Lake Tana (8,000–10,000 t/yr) and Lake Chamo (4,500 t/yr) lead production. Threats: deforestation, siltation, agrochemical runoff, and invasive water hyacinth (Emboch) on Lake Tana.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Largest vs. Longest River Basins: Wabishebelle has the LARGEST catchment area (202,697 km²) and is the longest river in Ethiopia; Abay has the LARGEST volumetric discharge (>54 BCM).
• River Destination Traps: Wabishebelle does NOT reach the Indian Ocean (disappears in Somali desert sands); Ghenale-Dawa reaches the Indian Ocean as the Juba River; Awash ends in Lake Abe.
• Deepest vs. Largest Lakes: Lake Tana is the LARGEST lake by surface area (3,600 km²); Lake Shalla is the DEEPEST lake (266 m); Lake Abaya is the LARGEST Rift Valley lake (1,162 km²).
• Sole Navigable River: The Baro River is the ONLY navigable river in Ethiopia (during rainy season on Gambella floodplains).
• Interconnected Rift Lakes: Both Lake Ziway (via Bulbula River) and Lake Langano (via Horocolo River) drain into terminal, alkaline Lake Abijata.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 86
    ),
    ShortNote(
        id = "geo-u5",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 5",
        title = "The Climate of Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Despite lying wholly within the tropical latitudes (3°N – 15°N), Ethiopia possesses remarkably diverse microclimates ranging from hot arid deserts to frigid alpine peaks. This diversity is driven by the interaction between the seasonal oscillation of the Intertropical Convergence Zone (ITCZ), planetary wind systems, and complex altitudinal relief.

🔍 DETAILED BREAKDOWN
1. Elements & Controls of Weather and Climate:
   • Weather vs. Climate: Weather represents the instantaneous state of the troposphere; Climate is the statistical composite of day-to-day weather conditions over decades.
   • Elements: Temperature, Precipitation, Humidity, Winds, Atmospheric Pressure.
   • Controls: Latitude, Altitude, Land/Water distribution, Pressure systems/Winds, Ocean currents.
   • Key Climatic Controls in Ethiopia:
     - Latitude: Dictates high insolation and solar angles year-round; high diurnal (daily) temperature range, small annual temperature range, and nearly equal day/night hours.
     - Earth's Axial Tilt & Seasons: Rotational axis tilted at 66½° to orbital plane (23½° from perpendicular). Equinoxes (March 21 & September 23: equal day/night); Solstices (Summer: June 21, sun over Tropic of Cancer; Winter: December 22, sun over Tropic of Capricorn).
     - Altitude & Lapse Rates: Most decisive local control.
       * Normal Environmental Lapse Rate: Temperature decreases by ~6.5°C per 1,000 m (1 km) rise in elevation.
       * Dry Adiabatic Lapse Rate (DALR): Unsaturated rising air cools at ~10°C per 1,000 m.
       * Wet/Moist Adiabatic Lapse Rate (WALR): Saturated rising air cools at ~5°C per 1,000 m (lower because condensation releases latent heat).

2. Spatiotemporal Distribution of Temperature & Rainfall:
   • Temperature Patterns: Mean annual temperatures range from >30°C in the Afar (Danakil) Depression to <10°C on high massifs (Simen, Bale). Maximum temperatures occur between March and June; minimums between November and February.
   • ITCZ Mechanism: Rainfall is governed by the seasonal latitudinal shift of the Intertropical Convergence Zone (ITCZ), where the dry Northeast Trade Winds converge with moist Equatorial Westerlies (Guinea Monsoons) and Indian Ocean easterlies.
     - In July: ITCZ shifts north of the Equator, drawing rain-bearing moist winds from the Atlantic and Indian Oceans over the Ethiopian highlands.
     - In January: ITCZ shifts south to the Tropic of Capricorn, allowing dry continental Northeast Trade Winds from Eurasia to dominate.
   • The Four Seasons:
     - Kiremt (Summer: June, July, August): Main rainy season for most of the country, fed by Equatorial Westerlies (Guinea Monsoon).
     - Tsedey (Autumn: September, October, November): ITCZ retreats southward; Indian Ocean southeasterlies bring rains to southeastern lowlands.
     - Bega (Winter: December, January, February): Dominant dry season; dry northeasterlies produce frost in highlands, but pick up Red Sea moisture to rain on Afar escarpments.
     - Belg (Spring: March, April, May): ITCZ migrates northward; moist Indian Ocean southeasterlies bring Belg rains to central and southeastern highlands.
   • Rainfall Regions:
     - Summer Rainfall Region: Northern, central, and western Ethiopia (>1,000 mm/yr in wet highlands).
     - All Year-Round Rainfall Region: Confined to southwestern highlands (humid Guinea monsoons: 1,400 to >2,200 mm/year; no dry season).
     - Autumn & Spring Rainfall Region: Southeastern lowlands (Ogaden, Borena; 60% autumn, 40% spring; <500 to 1,000 mm/year).
     - Winter Rainfall Region: Red Sea coastal plains and eastern Afar escarpment.

3. Traditional Agro-Ecological Zones of Ethiopia:
   • 1. Wurch (Cold to Moist / Alpine): Altitude >3,200 m; Mean Temp <10°C; Rainfall 900–2,200 mm; Area: 0.98%. Frost-prone high summits (Ras Dashen, Bale); afro-alpine moorland, light grazing, barley.
   • 2. Dega (Cool to Humid): Altitude 2,300–3,200 m; Mean Temp 10–15°C; Rainfall 900–1,200 mm; Area: 9.94%. Dense population, malaria-free; barley, wheat, pulses, sheep, cattle.
   • 3. Weyna Dega (Cool Sub-Humid / Temperate): Altitude 1,500–2,300 m; Mean Temp 15–20°C; Rainfall 800–1,200 mm; Area: 26.75%. Agricultural heartland; supports teff, maize, wheat, coffee, enset; two growing seasons.
   • 4. Kolla (Warm Semi-Arid): Altitude 500–1,500 m; Mean Temp 20–27.5°C; Rainfall 200–800 mm; Area: 52.94% (LARGEST zone by area). Peripheral lowlands, drought-prone; sorghum, millet, livestock pastoralism.
   • 5. Bereha (Hot Arid Desert): Altitude <500 m; Mean Temp >27.5°C; Rainfall <200 mm; Area: 9.39%. Danakil depression and deep lowlands; extreme heat, strong winds, sparse nomadic pastoralism.

4. Climate Change & Global Warming (Trends, Causes & Responses):
   • Observed Trends in Ethiopia:
     - Drought Vulnerability: Ranks 5th out of 184 nations; 12 major droughts between 1900 and 2010 (7 occurred after 1980).
     - Temperature Rise: Mean annual temperature increased by 1.3°C between 1960 and 2006 (0.20–0.28°C/decade); minimum temperatures rose even faster (0.37–0.40°C/decade).
     - Rainfall Variability: Seasonal rainfall in southern/southwestern/southeastern regions declined 15–20% (1975–2010), alongside intensified flash floods and lowland desertification.
   • Causes of Climate Change:
     - Natural: Milankovitch orbital cycles (eccentricity, axial tilt), solar variability, and volcanic aerosols altering planetary albedo.
     - Anthropogenic: Escalating greenhouse gases (CO₂ surged from pre-industrial 280 ppm to >400 ppm; CH₄ is less abundant but far more potent; N₂O, CFCs).
   • Impacts: Malaria transmission expanding to higher altitudes; crop failures and reduced growing days; diminished river runoff and groundwater recharge.
   • Response Strategies:
     - Mitigation (reducing emissions/enhancing sinks): Hydropower/solar/wind renewables, energy efficiency, reforestation and biological carbon sequestration.
     - Adaptation (adjusting to impacts): Flood diversion structures, rainwater harvesting, drought-tolerant crops, early warning systems, terracing and watershed restoration.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Largest Agro-Ecological Zone: Kolla is the LARGEST zone by surface area (52.94% of Ethiopia), while Wurch is the smallest (0.98%).
• Agricultural Heartland: Weyna Dega (1,500–2,300 m) is the primary agricultural zone due to moderate temperatures, fertile soils, and reliable rainfall.
• Lapse Rate Comparison: The Wet Adiabatic Lapse Rate (~5°C/km) is lower than the Dry Adiabatic Lapse Rate (~10°C/km) because latent heat of condensation warms the rising air parcel.
• All-Year-Round Rain: The southwestern highlands (e.g., Kaffa, Illubabor) are the only region in Ethiopia receiving rainfall in all 12 months (fed by equatorial Guinea monsoons).
• Winter Rain Exception: Red Sea coastal plains and eastern Afar escarpments receive winter (Bega) rainfall from marine-modified northeast trade winds.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 83
    ),
    ShortNote(
        id = "geo-u6",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 6",
        title = "Soils, Natural Vegetation and Wildlife Resources of Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia's intricate relief, geological diversity, and altitudinal climatic variations support a rich ecological mosaic. This unit covers the physical/chemical genesis and classification of Ethiopian soils, the vertical altitudinal zonation of natural vegetation, and exceptional wildlife diversity characterized by high endemicity and comprehensive protected national park networks.

🔍 DETAILED BREAKDOWN
1. Ethiopian Soils (Composition, Formation & Major Types):
   • Soil Composition (Typical Volumetric Ratio): Mineral matter (45%), Organic matter / Humus (5%), Soil air (20–30%), Soil water (20–30%).
   • Soil-Forming Factors: Parent rock material, Climate, Topography (relief), Living organisms, and Time.
   • Weathering Processes:
     - Mechanical (Physical): Thermal expansion/contraction, frost wedging (freezing water splitting rock), and wind/water abrasion.
     - Chemical: Hydrolysis, oxidation/reduction, hydration, carbonation, and mineral solution.
     - Biological: Root wedge splitting and organic secretions by burrowing fauna and microbes.
   • The 6 Major Soil Groups of Ethiopia:
     1. Nitosols & Acrisols:
        - Nitosols: Deep, well-drained, reddish-brown tropical soils on gently sloping volcanic plateaus (Wellega, Kaffa, Illubabor, Shewa). Heavily leached of calcium and potassium, but rich in iron and aluminum oxides; among the most fertile and cultivated highland soils.
        - Acrisols: Highly weathered, acidic soils in high-rainfall pockets of the southwest; low base saturation and poor natural fertility.
     2. Vertisols (Black Cotton Soils): Heavy, dark clay soils rich in montmorillonite swelling clays. Swell and become sticky/waterlogged when wet; shrink and crack deeply during dry periods. High chemical fertility, but physically difficult to plow and drain (Gojjam, Shewa, Arsi, Bale).
     3. Lithosols, Cambisols & Regosols: Shallow, stony, immature soils on steep slopes, Rift escarpments, and Chercher hills. Poor water retention; low agricultural utility; used for livestock grazing.
     4. Xerosols, Yermosols & Solonchaks: Arid, dry-steppe, and desert soils of the Ogaden and Danakil/Afar depressions. Low humus/nitrogen, but rich in soluble mineral salts; highly productive under irrigation. Solonchaks develop white saline surface crusts.
     5. Fluvisols (Alluvial Soils): Young, azonal sediments deposited by rivers on floodplains and deltas (lower Omo, Awash, Baro-Akobo, Rift lake plains). Highly fertile, replenished by annual floods, ideal for mechanized irrigation.
     6. Luvisols: Soils with distinct subsoil clay accumulation, moderate leaching, and high base saturation; excellent agricultural productivity (Lake Tana basin, central/eastern highlands).
   • Soil Degradation & Conservation:
     - Forms: Physical (compaction, water/wind erosion — estimated at 42 tons/hectare/year), Chemical (nutrient exhaustion, acidification, salinization), Biological (humus loss).
     - Conservation Measures: Biological (afforestation, reforestation, vegetative buffer strips, crop rotation) and Mechanical (contour terracing, check dams, gabions, stone bunds).

2. Natural Vegetation of Ethiopia:
   • Floral Diversity: ~6,000 species of higher plants, with ~10% endemism.
   • Major Vegetation Belts (Altitudinal Zonation):
     - 1. Afro-Alpine & Sub-Afro-Alpine (1.3% of country; highest in Africa):
       * Afro-Alpine (4,000–4,620 m): Sub-zero temperatures, thin rocky soils (Simen and Bale summits). Giant Lobelia (Lobelia rhynchopetalum / Giberra), tussock grasses, lichens.
       * Sub-Afro-Alpine (3,300–4,000 m): Erica arborea (Asta) woodland, heath scrub, and wet montane grasslands.
     - 2. Forest Region (450–3,500 m): Multi-layered canopies.
       * Highland Moist Forests: Hagenia abyssinica (Kosso), Juniperus procera (Tid), Podocarpus falcatus (Zigba), Arundinaria alpina (bamboo / Kerkha), Aningeria adolfi-friedericii (Keraro), Olea africana (Weyra).
       * Gallery (Riverine) Forests: Linear strips along arid lowland rivers (Awash, Wabishebelle); Ficus sur (Sholla) and riverine acacias.
     - 3. Woodland Savannah Region (250–2,300 m): Drought-resilient deciduous species. Juniperus woodlands (1,350–2,200 m), Acacia woodlands (Acacia etbaica / Grar, Acacia mellifera / Konter), and mixed deciduous woodlands.
     - 4. Steppe & Semi-Desert Regions: Arid lowlands. Steppe (100–1,400 m; 100–550 mm rain; perennial tussock grasses and dwarf acacias); Semi-Desert (-130 to 600 m; 50–300 mm rain; xerophytic thorny scrub and succulents).
   • Deforestation Drivers: Fuel-wood collection, charcoal production, agricultural encroachment, timber logging, and uncontrolled bushfires.

3. Wildlife Resources & Protected Areas:
   • Faunal Diversity & Endemism:
     - Birds: ~860 species (16 endemic species, 2 endemic genera).
     - Mammals: 279 species (31 endemic species, 6 endemic genera).
     - Amphibians: 23 species (23 endemic species — 100% ENDEMISM!).
     - Reptiles: 201 species (14 endemic); Freshwater Fish: 150 species (6 endemic).
   • Flagship Endemic Mammals:
     - Walia Ibex (Capra walie): Confined exclusively to the rocky crags of the Simen Mountains.
     - Mountain Nyala (Tragelaphus buxtoni): Native to Arsi and Bale montane massifs.
     - Ethiopian Wolf / Simen Fox (Canis simensis): Rare Afro-alpine predator in Bale (Sanetti) and Simen.
     - Gelada Baboon (Theropithecus gelada): Grass-eating highland primate with a distinctive red chest patch.
     - Starck’s Hare (Lepus starcki).
   • Selected Major National Parks:
     - Semien Mountains (1959, Amhara, 412 km²): Walia Ibex, Ethiopian Wolf, Gelada Baboon, Ras Dashen.
     - Bale Mountains (1962, Oromia, 2,200 km²): Mountain Nyala, Ethiopian Wolf, Sanetti Afro-alpine plateau.
     - Awash National Park (1958, Afar/Oromia, 756 km²): Beisa Oryx, gazelles, ostriches, Awash gorge falls.
     - Abijata-Shalla (1963, Oromia, 887 km²): Bird sanctuary; flamingo and pelican colonies.
     - Omo & Mago (1959/1974, SNNPR): Elephants, buffaloes, giraffes, hippos along Omo River.
     - Gambella (1966, Gambella, 4,650 km²): Nile lechwe, white-eared kob, wetland habitats.
     - Yangudi-Rassa (1969, Afar, 4,731 km²): Refuge for the endangered African wild ass.
     - Nechsar (1966, SNNPR, 514 km²): Plains zebras, Swayne’s hartebeest, Lake Abaya and Chamo views.
     - Kafta Shiraro (1999, Tigray, 5,000 km²): Trans-boundary elephant sanctuary.
   • Wildlife Conservation Challenges: Settlement/farming encroachment, illegal poaching, livestock competition for grazing, habitat fragmentation, and wildfires.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Endemism World Record: Ethiopian amphibians exhibit 100% endemism (all 23 documented species are found only in Ethiopia).
• Soil Swell-Shrink Trap: Vertisols (Black Cotton Soils) are chemically fertile but physically troublesome because they crack widely when dry and become unmanageably sticky and waterlogged when wet.
• Fertile Red Highland Soil: Nitosols are deep, well-drained, reddish-brown soils rich in iron and aluminum oxides; dominant in western/southwestern agricultural plateaus.
• Indicator Afro-Alpine Species: Giant Lobelia (Lobelia rhynchopetalum) characterizes Afro-alpine summits (>4,000 m), while Erica arborea (Asta) dominates the Sub-Afro-alpine zone (3,300–4,000 m).
• Flagship Mammal Habitats: Walia Ibex is restricted ONLY to the Simen Mountains; Mountain Nyala is endemic to the Bale-Arsi massifs.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 92
    ),
    ShortNote(
        id = "geo-u7",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 7",
        title = "Population of Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Human population represents both the creators and consumers of socioeconomic wealth. Ranking as the second most populous nation in Africa (surpassing 112–125 million), Ethiopia's demographic landscape is defined by rapid natural growth, an overwhelmingly young age structure, extreme altitudinal concentration in the highlands, and rapid urbanization despite a predominantly rural base.

🔍 DETAILED BREAKDOWN
1. Demographic Data Sources & National Census History:
   • Demographic Data Sources:
     - Census: Complete, simultaneous enumeration of all individuals in a territory at a fixed time; periodic (every 5–10 years) and government-sponsored.
       * De Jure Approach: Counts individuals at their permanent, usual place of residence (preferred for socioeconomic planning).
       * De Facto Approach: Counts individuals wherever they physically spend the census night (inflates holiday/transit locations).
     - Sample Surveys: Collects data from a representative subset; cheaper and faster, but prone to sampling errors.
     - Vital Registration: Continuous, legal, compulsory recording of vital events (births, deaths, marriages, divorces).
   • National Census History:
     - 1st Census (1984): 42.2 million (growth rate: 2.9% per year).
     - 2nd Census (1994): 53.5 million.
     - 3rd Census (2007): 73.8 million.
     - Recent Demographics: >112 million, predominantly rural (~80%), and highly youthful (~65% below 24 years).

2. Demographic Measurements & Vital Rates:
   • Core Demographic Formulas:
     - Crude Birth Rate (CBR): (Total Annual Live Births / Total Mid-Year Population) × 1,000.
     - General Fertility Rate (GFR): (Total Annual Live Births / Women aged 15–49) × 1,000.
     - Total Fertility Rate (TFR): Average number of children a woman bears over her reproductive lifespan.
     - Crude Death Rate (CDR): (Total Annual Deaths / Total Mid-Year Population) × 1,000.
     - Infant Mortality Rate (IMR): (Deaths of infants <1 year / Live Births) × 1,000.
     - Maternal Mortality Rate (MMR): (Pregnancy/childbirth maternal deaths / Live Births) × 100,000.
     - Natural Rate of Increase (NRI): (CBR - CDR) / 10 [%].
   • Demographic Trends in Ethiopia:
     - TFR dropped from 7.52 (1984) to 6.74 (1994) and 4.6 (2016). Large rural-urban divide: Urban TFR = 2.3 vs. Rural TFR = 5.2. Lowest in Addis Ababa (1.8); highest in Somali (7.2) and Afar (5.5).
     - Mortality Declines: IMR dropped from 97 per 1,000 live births (2000) to 54 (2016). Life expectancy rose from 36.7 years (1960s) to 62.6 years (65.4 females vs. 61.2 males).
     - Factors Sustaining High Fertility: Early marriage, low educational/economic status of women, cultural valuation of children as family labor/security assets, and low contraceptive prevalence.

3. Migration Dynamics & Irregular Migration Routes:
   • Internal Migration: Driven by highland landlessness, soil degradation, and rural underemployment.
     - Derg Era (1974–1991): Rural-urban migration suppressed by 1975 rural land proclamation, kebele associations, and conscription.
     - Post-1991: Regional administrative decentralization; ~16.6% of population classified as internal migrants (2007).
   • International Migration & Major Irregular Outflow Routes:
     - 1. Eastern Route (60–70% of irregular migrants): From Wollo, Arsi-Bale, Tigray through Djibouti/Somalia across the Red Sea/Gulf of Aden and Yemen into the Middle East.
     - 2. Northern / Western Route: Through Sudan, Egypt, and Libya across the Mediterranean Sea to Europe.
     - 3. Southern Route: Overland through Kenya and Tanzania toward South Africa.
   • Refugee Hosting: Ethiopia hosts nearly 1 million refugees and asylum seekers (2nd largest in Africa), primarily from South Sudan, Somalia, Eritrea, and Sudan.

4. Age-Sex Structure & Spatial Population Distribution:
   • Age Structure & Dependency Ratios:
     - Broad Cohorts: Young Age (0–14) = ~45.0%; Working Age (15–64) = ~51.9%; Old Age (65+) = ~3.2%.
     - Median Age: ~17 years (broad-based, rapidly tapering pyramid indicating high fertility and high dependency).
     - Total Age Dependency Ratio (ADR) = [(P_0-14 + P_65+) / P_15-64] × 100 (~82–93 dependents per 100 workers).
   • Sex Structure: Sex ratio = (Males / Females) × 100 (National ratio near balance ~99.96–101.3). Rural sex ratio is higher than urban because of heavy female rural-to-urban domestic labor migration.
   • Measures of Density:
     - Crude (Arithmetic) Density = Population / Land Area (increased from 40.7/km² in 1990 to >100/km²; SNNPR = 173.3 vs. Gambella = 13.7, Somali = 19.5).
     - Physiological Density = Population / Arable Land (61.8/km² in 1998).
     - Agricultural Density = Agricultural Population / Cultivated Land (highest in SNNPR and pastoral regions).
   • Altitudinal Concentration:
     - 77.5% of the population lives above 1,800 m (occupying only 37.6% of national land area).
     - 89.0% lives above 1,400 m (65.7% of land area).
     - Lowlands (<1,000 m) cover 21.5% of Ethiopia but house ONLY 2.8% of the population due to extreme heat, aridity, and malaria.

5. Socio-Cultural Diversity & Urbanization:
   • Languages & Super Families (~80 spoken languages):
     - 1. Afro-Asiatic Super Family:
       * Cushitic (largest speaker base): Afan Oromo (33.8%), Somaligna (6.2%), Sidamigna (4.0%), Afarigna (1.7%), Hadiyigna, Kembatigna, Gedeogna.
       * Semitic: Amharic (29.3%), Tigrigna (5.9%), Guragigna (2%), Siltigna, Harari (Aderigna), Argobba.
       * Omotic: Wolaytigna (2.2%), Gamogna (1.5%), Gofigna, Keffigna.
     - 2. Nilo-Saharan Super Family: Spoken along western borderlands (Nuer, Anuak, Gumuz, Berta, Kunama, Komo, Mao).
   • Urbanization Characteristics:
     - Criteria: Minimum 2,000 population, 2/3 engaged in non-agricultural pursuits, and chartered municipality status.
     - Low Level vs. Rapid Growth: Only ~20% of the population is urban (one of the least urbanized globally), but urban growth rates are among the highest (~5% annually).
     - Urban Primacy: Addis Ababa is a classic primate city housing ~29% of the urban population. Secondary clusters include Lake Tana, South Rift Valley (Hawassa-Shashemene-Dilla), Eastern (Dire Dawa-Harar-Jigjiga), Mekelle, and Jimma clusters.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• De Jure vs. De Facto Census: De Jure counts people at their permanent residence (best for planning); De Facto counts people where they are found on census night.
• Dominant Outflow Route: The Eastern Route (via Djibouti/Somalia to Yemen and Saudi Arabia) accounts for 60–70% of all irregular Ethiopian migration.
• Extreme Altitudinal Concentration: 77.5% of Ethiopia's population lives above 1,800 meters, while lowlands (<1,000 m) contain only 2.8% of the population!
• Language Demographics: Cushitic is the largest linguistic family; Afan Oromo is the most widely spoken language (33.8%), followed by Amharic (29.3%).
• Urbanization Paradox: Low urbanization level (~20%) coupled with an exceptionally high urban growth rate (~5% per year).
• Sex Ratio Contrast: Urban sex ratios are LOWER than rural sex ratios because more rural females migrate to urban centers for domestic employment.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 88
    ),
    ShortNote(
        id = "geo-u8",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 8",
        title = "Economic Activities in Ethiopia and the Horn",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Economic activities are human actions undertaken to satisfy material and spiritual needs, spanning primary extraction, secondary manufacturing, and tertiary services. Agriculture remains the foundational backbone of Ethiopia's economy, supported by strategic mining, inland fisheries, rapidly expanding industrial mega-parks, and modernized trade and transport infrastructure.

🔍 DETAILED BREAKDOWN
1. Economic Classification & Mining Activity:
   • Sector Continuum: Primary (direct natural extraction: farming, mining, forestry, fishing), Secondary (industrial transformation into manufactured goods), Tertiary (service provision: trade, transport, tourism, banking), Quaternary & Quinary (information, research, governance).
   • Mining Sector in Ethiopia: Contributes ~1.5% to GDP (up to 6% potential); generates ~$135 million USD annually in foreign exchange (7–10% of visible exports).
     - Gold (Au): Primary lode deposits at Lege-dembi (82-ton reserve, ~3.6 tons/year) and Sakaro; over 1,000 kg/year of placer/alluvial gold purchased by National Bank of Ethiopia from Adola, Benishangul-Gumuz (Sherkole, Asosa), Akobo (Gambella), and Wellega.
     - Tantalum / Niobium: Kenticha open-pit pegmatite mine in Adola holds world-class reserves (>2,400 tons Ta₂O₅, >2,300 tons Nb₂O₅), supplying ~10% of global tantalum for electronics (smartphones, capacitors).
     - Platinum (Pt): Yubdo area in western Wellega (weathered laterites).
     - Soda Ash (Na₂CO₃): Lake Abijata brines (>460 million tons reserve) for soap and detergent industries.
     - Potash & Halite: Vast evaporites in the Danakil (Dallol) Depression of Afar.
     - Natural Gas: 4.6 Trillion Cubic Feet (TCF) proven reserves in the Ogaden Basin (Calub, Hilala, Genale).
     - Coal (Lignite): Interbedded in Tertiary sediments at Nedjo (Wellega), Chilga (Gondar), and Muger/Didessa valleys.
     - Gemstones & Construction Materials: Precious Opal (Wadla & Dalanta in North Wollo), amethyst, emerald; limestone, marble, basalt, and scoria for cement and construction.
     - Environmental Regulations: 1993 Mining Legislation mandates Environmental Impact Assessments (EIAs) and rehabilitation sinking funds.

2. Forestry and Fishery Resources:
   • Forestry Sector: Contributes ~2.5% of GDP; primarily harvested for household fuel-wood, charcoal, building poles, and timber. Constrained by severe historical deforestation and remote high-forest locations.
   • Fishery Potential & Fishing Grounds:
     - Fish Diversity: >100 freshwater species (25 commercial, 4 endemics); catch dominated by Tilapia (~50%), Catfish/Clarias (~22%), Nile Perch (~9%), and Barbus.
     - Fishing Grounds: Inland lakes cover ~7,000 km² and account for 79% of catch (Lake Tana: 15,000 tons/yr potential; Lake Abaya: 7,000 t; Ziway: 3,000 t; Chamo: 3,000 t). Rivers provide 7,000–10,000 t/yr potential (Baro River alone supplies ~2,500 t/yr).
     - Underutilization: Estimated national sustainable yield is 30,000–51,481 tons/year, while actual annual harvest is only 10,000–15,389 tons.
     - Low Consumption Trap: Per capita consumption is only 0.3 kg/year (vs. East African average of 2.6 kg) due to non-fish dietary habits, religious fasting cycles, high prices, and lack of cold-chain refrigerated transport.

3. Agriculture in Ethiopia (Backbone of the Economy):
   • National Stature: Generates 30–42% of GDP, >90% of merchandise exports, and employs >80% of the labor force.
   • Agricultural Resource Base: Vast grazing land (55–58% of national area) supporting Africa's largest livestock herd (30–35 million TLU, 10th in the world); 3.5–5.3 million hectares irrigable land (only ~4.6% currently developed).
   • Cropping Seasons & Production:
     - Meher (Main Season: Sep–Feb): Fed by Kiremt rains; produces 90–95% of national cereal output.
     - Belg (Secondary Season: Mar–Aug): Produces 5–10% of cereal output.
     - Major Crops: Cereals (teff, wheat, maize, barley, sorghum) dominate >10.2 million hectares (>267 million quintals), followed by pulses and oilseeds.
     - Livestock: 75% of cattle and sheep are concentrated in the highlands; 70% of goats and 100% of camels inhabit peripheral lowlands. Hides and skins contribute >95% of livestock export value.
   • The 5 Major Farming Systems:
     1. Highland Mixed Farming (>1,500 m):
        * Grain-Plough Complex: Ox-drawn plough, cereals (teff, wheat, barley), northern and central plateaus.
        * Horticulture-Hoe Complex: Human hoe cultivation, perennial enset (false banana), coffee, chat, southern/southwestern highlands.
     2. Lowland Mixed Agriculture (<1,500 m): Drought-tolerant crops (sorghum, millet) combined with livestock grazing.
     3. Pastoral System (<500 mm rain): Nomadic/transhumant herding of camels, cattle, and shoats in Afar, Somali, and Borena; supplies >90% of live animal exports.
     4. Shifting Cultivation: Traditional slash-and-burn farming along western lowland fringes (Benishangul-Gumuz, Gambella).
     5. Commercial Agriculture: Modern, mechanized, capital-intensive plantations (Awash valley, Humera-Metema, Rift lakes: sugar, cotton, floriculture).
   • Structural Constraints: Subsistence orientation (smallholders retain up to 80% of output), severe plot fragmentation (0.28–1.0 ha per household), low external inputs, and extreme rain-fed vulnerability (>98%).

4. Manufacturing Industry & Industrial Parks Strategy:
   • Economic Position: Industry contributes ~11.7% of GDP (manufacturing is 4.0–4.9%), employing >329,000 formal workers. Dominated by light consumer industries (food and beverages represent the largest share, followed by leather, textiles, and cement).
   • Spatial Concentration: Heavy agglomeration in the Addis Ababa Metropolitan Cluster (37–40% of enterprises, ~46% of employment), followed by Oromia (27%), Tigray (14%), Dire Dawa, Hawassa, and Bahir Dar.
   • Industrial Parks (IPs) & Mega-Projects:
     - Export-Oriented Federal Parks (Proclamation 886/2015): Hawassa, Bole Lemi I & II, Kilinto, Kombolcha, Adama, Dire Dawa, Bahir Dar.
     - Integrated Agro-Industrial Parks (IAIPs): Bure (Amhara), Bulbula (Oromia), Yirgalem (Sidama), Baeker (Tigray).
     - Sugar Sector Mega-Projects: 10 large-scale sugarcane plantations and factories (Tendaho, Kessem, Omo-Kuraz, Arjo-Didessa, Finchaa, Beles).

5. The Service Sector (Transportation, Trade & Tourism):
   • Transport Systems:
     - Road Transport: Dominant mode (>95% passenger and freight); network expanded from 37,018 km (2005) to 126,773 km (2018); road density reached 115.2 km/1,000 km².
     - Railways: Electrified standard-gauge Addis Ababa–Djibouti Railway (750 km opened 2018 to Port Doraleh, handling >95% of Ethiopia's external trade); Addis Ababa Light Rail Transit (AALRT, 31.6 km, 60k passengers/day).
     - Waterways & Aviation: Lower Baro River and Lakes Tana/Abaya; Ethiopian Airlines (EAL) connects 45 domestic airports and operates Africa's largest international aviation network.
   • Foreign Merchandise Trade:
     - Structural Trade Deficit: Merchandise imports heavily exceed exports.
     - Exports: Highly concentrated in agricultural primary commodities: Coffee (29.5–30.4%), Oilseeds (14.9%), Pulses (9.5%), Chat (9.3–9.5%), Cut flowers (~8.0%), Gold (3.5–10%), Leather (4.7%).
     - Destination/Origin: Exports go to Asia (39.8%), Europe (28.7%), Africa (20.9%); Imports come from Asia (64.2%), Europe (19.3%), USA (9.4%) (dominated by China, USA, Japan, India: machinery, petroleum, industrial chemicals).
   • Tourism Industry: Contributes ~2.7% to GDP and supports ~604,000 jobs.
     - Cultural Sites (UNESCO): Rock-Hewn Churches of Lalibela, Axum Obelisks, Gondar Castles (Fasil Ghebbi), Harar Jugol walls.
     - Natural Attractions: Simien Mountains (Ras Dashen, Walia Ibex), Bale Mountains (Sanetti, Mountain Nyala), Tisissat (Blue Nile) Falls, and Rift Valley bird sanctuaries.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Top Export Commodity: Coffee is by far the leading foreign exchange earner (~30% of exports), followed by oilseeds and pulses.
• Strategic Global Mineral: Kenticha mine in Adola holds world-class reserves of tantalite-columbite, supplying nearly 10% of global tantalum for microelectronics.
• Seasonal Cereal Output: The Meher season produces 90–95% of national cereals, whereas the Belg season yields only 5–10%.
• Maritime Trade Corridor: Over 95% of landlocked Ethiopia's external trade flows through the Port of Djibouti via the 750-km electrified Addis Ababa–Djibouti railway.
• Fishery Consumption Paradox: Despite water bodies holding a sustainable capacity >50,000 tons/yr, annual per capita intake is only 0.3 kg due to cultural food habits and religious fasting cycles.
• Industrial Agglomeration: Addis Ababa and surrounding Oromia special zones account for over 40% of manufacturing enterprises and industrial jobs.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 95
    ),

    // =========================================================================
    // 7. FRESHMAN COC (c7, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "coc-u1",
        subjectId = "c7",
        subject = "Freshman COC",
        unit = "Unit 1",
        title = "Natural Science Stream Core Synthesis (Math, Physics & Tech)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Freshman Common Course Certification (COC) in Natural Science tests cross-disciplinary integration. Success requires applying mathematical tools (calculus, vectors, geometry) directly to solve physics and computational problems.

🔍 DETAILED BREAKDOWN
1. Mathematics-to-Physics Cross-Disciplinary Mappings:
   • Kinematics & Calculus:
     - Position x(t) -> Velocity v(t) = dx/dt -> Acceleration a(t) = dv/dt = d²x/dt².
     - Displacement Δx = ∫ v(t) dt; Velocity change Δv = ∫ a(t) dt.
   • Work & Integral Calculus:
     - Work W = ∫ F · dr. For variable force along x-axis: W = ∫ F(x) dx.
     - Impulse J = ∫ F(t) dt = Δp.
   • Center of Mass & Definite Integrals:
     - x_cm = (1/M) ∫ x dm, where dm = λ dx (1D rod) or dm = σ dA (2D plate).

2. Core Conservation Laws Synthesis:
   • Conservation of Mechanical Energy: K_i + U_i = K_f + U_f (isolated, conservative forces).
   • Conservation of Linear Momentum: Σ p_i = Σ p_f (when ΣF_ext = 0).
   • Master Equation for Collisions: In 1D elastic collisions, relative velocity of approach equals relative velocity of separation: (u₁ - u₂) = -(v₁ - v₂).

3. Emerging Tech & Computing Foundations:
   • Big Data 5 V's: Volume, Velocity, Variety, Veracity, Value.
   • Artificial Neural Networks (ANN): Inspired by biological neurons. Perceptron weights and biases adjusted via backpropagation and gradient descent.
   • Cloud Service Models: IaaS (raw infrastructure/VMs), PaaS (development environment/runtime), SaaS (ready end-user applications).

💡 HIGH-YIELD COC FORMULA CHEAT SHEET
• Projectile Max Height: H = (v₀² sin²θ) / (2g); Range: R = (v₀² sin 2θ) / g.
• Centripetal Force: F_c = m v² / r = m ω² r.
• Derivative of Quotient: (u/v)' = (u'v - uv') / v².
• Integration by Parts: ∫ u dv = uv - ∫ v du.
        """.trimIndent(),
        colorHex = 0xFF7C3AED,
        initialLikes = 96
    ),
    ShortNote(
        id = "coc-u2",
        subjectId = "c7",
        subject = "Freshman COC",
        unit = "Unit 2",
        title = "Social Science Stream Core Synthesis (Economics, Civics & Geography)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The Social Science COC evaluates structural understanding of human societies, institutional mechanisms, market dynamics, and spatial-demographic patterns in Ethiopia and globally.

🔍 DETAILED BREAKDOWN
1. Economics Core Synthesis:
   • Market Equilibrium: Q_d = Q_s. Price below equilibrium causes shortage (excess demand); price above causes surplus (excess supply).
   • Elasticity: PED = (%ΔQ_d) / (%ΔP). Elastic (|e| > 1) vs Inelastic (|e| < 1).
     - Total Revenue Test: If demand is elastic, lowering price increases TR; if inelastic, raising price increases TR.
   • Production & Cost: Law of Diminishing Marginal Returns dictates that Marginal Cost (MC) intersects Average Total Cost (ATC) and Average Variable Cost (AVC) at their absolute minimum points.

2. Moral & Civics Core Synthesis:
   • Ethical Theories: Utilitarianism (consequentialist: greatest good for greatest number) vs Kantian Deontology (duty-based: categorical imperative) vs Aristotelian Virtue Ethics (character, golden mean).
   • Constitutional Principles: Popular sovereignty (FDRE Art. 8), Constitutional supremacy (Art. 9), Secularism (Art. 11), Accountability & Transparency (Art. 12).
   • Three Generations of Human Rights: 1st Gen (Civil & Political), 2nd Gen (Socio-Economic), 3rd Gen (Solidarity/Group rights).

3. Geography Core Synthesis:
   • Altitudinal Zonation: Bereha (<500m), Kolla (500-1500m), Woina Dega (1500-2300m - densest settlement), Dega (2300-3200m), Wurch (>3200m).
   • Drainage Systems: Western (Abay, Baro - drains ~60% to Nile), Southeastern (Wabe Shebelle, Genale-Dawa), Inland (Awash, Omo).

💡 HIGH-YIELD COC SOC-SCI CHEAT SHEET
• In perfect competition: P = MR = AR = MC.
• FDRE Constitution: Enacted in 1995; established 9 regional states originally and ethnic-based federalism.
• Demographics: Over 80% of Ethiopians live above 1,500m altitude.
        """.trimIndent(),
        colorHex = 0xFF7C3AED,
        initialLikes = 91
    ),
    ShortNote(
        id = "coc-u3",
        subjectId = "c7",
        subject = "Freshman COC",
        unit = "Unit 3",
        title = "Exam Strategy, Pacing & Time Management Systems",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
University COC exams feature strict time constraints (typically 100 questions in 120 minutes = 1.2 minutes per question). Maximizing scores requires deliberate tactical pacing, multi-pass screening, and elimination heuristics.

🔍 DETAILED BREAKDOWN
1. The Three-Pass Strategy:
   • Pass 1 (Instant Recall & Direct Computation - Minutes 0 to 45):
     - Answer every question that you know immediately with 100% confidence.
     - Skip and flag any question requiring multi-step calculations, extensive paragraph reading, or complex deliberation.
     - Goal: Bank 40-50 guaranteed correct answers rapidly to relieve psychological anxiety.
   • Pass 2 (Analytical & Calculated Problems - Minutes 45 to 95):
     - Tackle flagged numerical problems (calculus derivatives, kinematics trajectories, economics elasticity math).
     - Work through logic validity checks and reading comprehension passages.
   • Pass 3 (Tough Questions & Strategic Educated Guessing - Minutes 95 to 120):
     - Resolve remaining difficult questions.
     - Eliminate obvious distractors, choose the highest-probability option, and ensure NO question is left blank.

2. Distractor Elimination Heuristics:
   • Absolute / Extreme Language: Options containing words like "always", "never", "all", "impossible", "solely" are statistically incorrect in 85%+ of social science and psychology questions.
   • Nuanced / Qualified Language: Options containing qualifiers like "often", "may", "typically", "tend to", "can be" are far more likely to be correct.
   • Opposites Pair Rule: If two options directly contradict each other, the correct answer is almost always one of those two.
   • Grammatical Clueing: If the question stem ends in "an", the correct option must start with a vowel sound.

💡 HIGH-YIELD COC TACTICAL RULES
• Never spend more than 2.5 minutes on any single question during the first pass! If stuck, flag it and move forward.
• There is NO negative marking in Ethiopian university freshman COC exams — never leave any bubble unfilled!
        """.trimIndent(),
        colorHex = 0xFF7C3AED,
        initialLikes = 105
    ),
    ShortNote(
        id = "coc-u4",
        subjectId = "c7",
        subject = "Freshman COC",
        unit = "Unit 4",
        title = "Quantitative & Critical Reasoning Master Techniques",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The Quantitative and Critical Reasoning component evaluates analytical intelligence: interpreting mathematical patterns, assessing verbal arguments, identifying logical fallacies, and solving rate-ratio problems.

🔍 DETAILED BREAKDOWN
1. Formal Logic Testing in Exams:
   • Syllogism Validation:
     - Universal Affirmative (All S are P): Can never guarantee "All P are S" (illegal conversion).
     - Valid forms to memorize: Modus Ponens (If P then Q; P; therefore Q); Modus Tollens (If P then Q; Not Q; therefore Not P).
     - Fallacious forms: Affirming the Consequent (If P then Q; Q; therefore P - INVALID); Denying the Antecedent (If P then Q; Not P; therefore Not Q - INVALID).

2. Quantitative Aptitude Shortcuts:
   • Ratio, Percentage & Work-Rate Problems:
     - Combined Work: If A completes a job in T_A days and B in T_B days, together they complete it in:
       T_together = (T_A · T_B) / (T_A + T_B).
     - Percentage Change: %Δ = [(New - Old) / Old] × 100%.
   • Relative Velocity:
     - Two objects moving toward each other: v_rel = v₁ + v₂.
     - Two objects moving in same direction: v_rel = |v₁ - v₂|.

3. Critical Reading & Inference Questions:
   • Differentiating Fact vs Assumption vs Inference:
     - Fact: Explicitly stated in the text.
     - Assumption: Unstated premise necessary for the author's argument to hold true.
     - Inference: Logical conclusion drawn from stated premises that MUST be true based on the passage.
   • Beware of the "True in Reality, but False in Context" trap: If a passage states "All dogs have wings", answer questions based SOLELY on the given passage premises.

💡 HIGH-YIELD COC REASONING RULES
• For Modus Ponens & Modus Tollens: Affirming the first is valid, denying the second is valid. Affirming the second or denying the first is ALWAYS a fallacy.
• In percentage problems, watch whether the base is original price or discounted price.
        """.trimIndent(),
        colorHex = 0xFF7C3AED,
        initialLikes = 94
    ),
    ShortNote(
        id = "coc-u5",
        subjectId = "c7",
        subject = "Freshman COC",
        unit = "Unit 5",
        title = "High-Yield Traps, Tricky Questions & Common Exam Pitfalls",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Exam creators repeatedly design questions around predictable conceptual confusions. Reviewing these high-yield trap concepts prevents losing easy marks on standardized tests.

🔍 DETAILED BREAKDOWN
1. Physics & Math High-Yield Traps:
   • Sign of Gravity in Projectiles: Vertical acceleration is ALWAYS downward: a_y = -g (-9.8 m/s²). At peak height, vertical velocity v_y = 0, but acceleration is STILL -g, NOT zero!
   • Normal Force on Slopes: N = mg cos θ (NOT mg).
   • Work by Friction: Kinetic friction work is always negative (-f_k d).
   • L'Hôpital's Rule Misuse: Check that the limit form is STRICTLY 0/0 or ∞/∞ before taking derivatives. Differentiate numerator and denominator separately (do NOT use quotient rule).
   • Continuity vs Differentiability: Continuity does NOT guarantee differentiability! Sharp corners (like |x| at x=0) and vertical tangents are continuous but not differentiable.

2. Economics High-Yield Traps:
   • Shift in Demand vs Movement Along Demand Curve:
     - Change in PRICE of the good causes MOVEMENT along the curve (Change in Quantity Demanded).
     - Change in income, tastes, or substitute prices causes a SHIFT of the entire curve (Change in Demand).
   • Inelastic vs Elastic Total Revenue: Raising price on an inelastic product INCREASES total revenue; raising price on an elastic product DECREASES total revenue.

3. Psychology & Logic High-Yield Traps:
   • Negative Reinforcement vs Punishment: Negative reinforcement INCREASES behavior by removing something bad; punishment DECREASES behavior.
   • Memory Interference: Proactive = Old disrupts new; Retroactive = New disrupts old.
   • Validity vs Soundness: An argument can be completely VALID even if its premises are completely absurd and factually false. Soundness requires BOTH validity and factual truth.

💡 THE GOLDEN EXAM RULE
• Read the question stem thoroughly: Watch for negative words like "EXCEPT", "NOT", "LEAST likely", or "INCORRECT". Circling these words prevents answering the opposite!
        """.trimIndent(),
        colorHex = 0xFF7C3AED,
        initialLikes = 112
    ),

    // =========================================================================
    // 8. EMERGING TECHNOLOGIES (c8, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "tech-u1",
        subjectId = "c8",
        subject = "Emerging Technologies",
        unit = "Unit 1",
        title = "Fourth Industrial Revolution (4IR) & Technological Evolution",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The Fourth Industrial Revolution (4IR) represents the convergence of digital, biological, and physical domains. Driven by cyber-physical systems, exponential computing power, and ubiquitous connectivity, 4IR is transforming global economies, industries, and governance.

🔍 DETAILED BREAKDOWN
1. Historical Evolution of Industrial Revolutions:
   • 1st Industrial Revolution (1IR, ~1780s):
     - Key Drivers: Water and steam power, mechanization of production, mechanized textile looms.
   • 2nd Industrial Revolution (2IR, ~1870s):
     - Key Drivers: Electricity, internal combustion engine, mass production, assembly line (Henry Ford), petroleum.
   • 3rd Industrial Revolution (3IR / Digital Revolution, ~1970s):
     - Key Drivers: Semiconductors, mainframe computers, personal computers, automation, early internet.
   • 4th Industrial Revolution (4IR, Present):
     - Key Drivers: Fusion of technologies blurring boundaries between physical, digital, and biological spheres; Artificial Intelligence, Internet of Things (IoT), Big Data, Cloud Computing, Quantum Computing, Robotics, 3D printing, Gene editing (CRISPR).

2. Core Characteristics of 4IR:
   • Velocity: Exponential pace of technological emergence and diffusion compared to historical linear speeds.
   • Breadth & Depth: Systemic transformation across every industry, discipline, and economy.
   • Systems Impact: Complete overhaul of governance, supply chains, management, and societal interactions.

3. Key Enabling Technologies of 4IR:
   • Cyber-Physical Systems (CPS): Integrations of computation, networking, and physical processes monitored by computer-based algorithms.
   • Artificial Intelligence & Machine Learning.
   • Big Data Analytics & High-Performance Cloud Computing.
   • Autonomous Robotics, Additive Manufacturing (3D Printing), Nanotechnology, and Synthetic Biology.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The defining trademark of 4IR is the FUSION of the physical, digital, and biological domains.
• 1IR = Steam; 2IR = Electricity/Assembly line; 3IR = Computers/Electronics; 4IR = Cyber-Physical Systems & AI.
        """.trimIndent(),
        colorHex = 0xFF6366F1,
        initialLikes = 83
    ),
    ShortNote(
        id = "tech-u2",
        subjectId = "c8",
        subject = "Emerging Technologies",
        unit = "Unit 2",
        title = "Data Science, Big Data Architecture & Analytics Pipeline",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Data Science is an interdisciplinary field using scientific methods, algorithms, and processes to extract actionable knowledge and insights from structured and unstructured data. Big Data refers to datasets whose scale and complexity exceed traditional database capabilities.

🔍 DETAILED BREAKDOWN
1. The 5 V's of Big Data:
   • 1. Volume: Massive scale of data generated (measured in Terabytes, Petabytes, Exabytes, Zettabytes).
   • 2. Velocity: Incredible speed at which data is generated, streamed, and processed in real time (e.g., IoT sensors, financial trades, social feeds).
   • 3. Variety: Multiplicity of data types and formats:
     - Structured: Relational tables (SQL, rows & columns).
     - Semi-Structured: XML, JSON, CSV files.
     - Unstructured: Audio, video, PDF, satellite imagery, raw text (constitutes >80% of enterprise data).
   • 4. Veracity: Trustworthiness, quality, clean accuracy, and noise level of data.
   • 5. Value: Actionable business or scientific value derived from data analytics.

2. Data Science Lifecycle & Pipeline:
   • 1. Problem Formulation: Identifying objectives and domain requirements.
   • 2. Data Acquisition: Gathering data via web scraping, APIs, databases, or IoT sensors.
   • 3. Data Cleaning & Preprocessing: Handling missing values, removing outliers, deduplication, normalizing, and encoding categorical variables.
   • 4. Exploratory Data Analysis (EDA): Visualizing distributions, correlations, and anomalies.
   • 5. Feature Engineering: Selecting and transforming relevant variables for modeling.
   • 6. Model Training & Evaluation: Applying ML algorithms; cross-validation; metrics (accuracy, precision, recall, F1-score).
   • 7. Deployment & Monitoring: Integrating model into live production environment.

3. Four Levels of Data Analytics:
   • Descriptive Analytics: "What happened?" (Reports, dashboards, historical metrics).
   • Diagnostic Analytics: "Why did it happen?" (Root-cause analysis, drill-down).
   • Predictive Analytics: "What is likely to happen?" (Forecasting using ML models).
   • Prescriptive Analytics: "What should we do about it?" (Optimization algorithms, automated action recommendation).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Memorize the 5 V's: Volume, Velocity, Variety, Veracity, and Value.
• Prescriptive analytics is the highest, most sophisticated level of analytics, recommending optimal decision choices.
        """.trimIndent(),
        colorHex = 0xFF6366F1,
        initialLikes = 88
    ),
    ShortNote(
        id = "tech-u3",
        subjectId = "c8",
        subject = "Emerging Technologies",
        unit = "Unit 3",
        title = "Artificial Intelligence, Machine Learning & Neural Networks",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Artificial Intelligence (AI) enables machines to simulate human cognitive functions such as learning, reasoning, problem-solving, and perception. Machine Learning (ML) is a subset of AI where systems learn from data without explicit programming.

🔍 DETAILED BREAKDOWN
1. Levels & Classifications of AI:
   • Artificial Narrow Intelligence (ANI / Weak AI): Designed for a single specific task (e.g., Siri, AlphaGo, facial recognition, spam filters). All current AI is ANI.
   • Artificial General Intelligence (AGI / Strong AI): Theoretical AI with human-level cognitive capabilities across any domain.
   • Artificial Superintelligence (ASI): Theoretical future AI surpassing human intelligence across all fields.

2. Three Primary Machine Learning Paradigms:
   • Supervised Learning:
     - Learns from labeled training datasets (input-output pairs).
     - Classification: Predicting discrete categorical classes (e.g., spam vs non-spam, disease diagnosis). Algorithms: Logistic Regression, Decision Trees, Support Vector Machines (SVM), Random Forest.
     - Regression: Predicting continuous numerical values (e.g., house prices, stock values). Algorithms: Linear Regression, Polynomial Regression.
   • Unsupervised Learning:
     - Discovers hidden patterns in unlabeled data.
     - Clustering: Grouping similar instances without prior labels. Algorithms: K-Means, Hierarchical Clustering.
     - Dimensionality Reduction: Compressing features while retaining variance (e.g., Principal Component Analysis - PCA).
   • Reinforcement Learning (RL):
     - An agent interacts with an environment, learning optimal policy via trial and error using Rewards and Penalties (e.g., autonomous driving, game playing like chess).

3. Deep Learning & Artificial Neural Networks (ANN):
   • Inspired by biological neurons.
   • Architecture: Input Layer -> Multiple Hidden Layers -> Output Layer.
   • Perceptron: Multiplies inputs by adjustable weights, adds bias, and passes sum through an Activation Function (Sigmoid, ReLU, Tanh).
   • Specialized Architectures:
     - Convolutional Neural Networks (CNN): Specialized for computer vision and image processing.
     - Recurrent Neural Networks (RNN) & Transformers: Specialized for sequential data, natural language processing (NLP), and large language models (LLMs).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Supervised learning uses LABELED data; Unsupervised learning uses UNLABELED data.
• CNNs are optimized for spatial/image data; RNNs and Transformers are optimized for sequential/text data.
        """.trimIndent(),
        colorHex = 0xFF6366F1,
        initialLikes = 101
    ),
    ShortNote(
        id = "tech-u4",
        subjectId = "c8",
        subject = "Emerging Technologies",
        unit = "Unit 4",
        title = "Internet of Things (IoT), Architecture & Smart Applications",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The Internet of Things (IoT) is a global network of physical devices, vehicles, appliances, and sensors embedded with electronics, software, and connectivity that enable them to collect and exchange data autonomously.

🔍 DETAILED BREAKDOWN
1. Four-Layer IoT Architecture:
   • 1. Perception (Sensing) Layer:
     - Physical hardware layer containing sensors and actuators.
     - Sensors detect physical parameters (temperature, humidity, pressure, light, motion, GPS).
     - Actuators perform physical actions (turning on motors, opening valves).
     - Technologies: RFID tags, QR codes, ZigBee, Bluetooth Low Energy (BLE).
   • 2. Network (Transmission) Layer:
     - Transmits gathered data from perception layer to processing systems.
     - Technologies: Wi-Fi, 4G/5G, LoRaWAN (Long Range Wide Area Network), Ethernet, NB-IoT.
   • 3. Middleware / Processing Layer:
     - Cloud computing, edge analytics, databases, and message brokering (MQTT, CoAP protocols).
     - Stores, filters, and analyzes massive streaming sensor data.
   • 4. Application Layer:
     - User-facing interfaces deliver smart services (Smart Homes, Smart Health, Precision Agriculture, Smart Cities).

2. Fog & Edge Computing vs Cloud Computing in IoT:
   • Cloud Computing: Centralized processing at distant data centers; high compute power but higher latency.
   • Edge Computing: Processing data locally right at the device or local gateway (e.g., autonomous vehicle braking decision). Provides near-zero latency, saves network bandwidth, and enhances privacy.
   • Fog Computing: Decentralized computing layer positioned between edge devices and the centralized cloud.

3. Major IoT Application Domains:
   • Smart Cities: Automated traffic light control, smart street lighting, automated waste management.
   • Precision Agriculture: Soil moisture sensors triggering automated drip irrigation, drone crop health monitoring.
   • Healthcare (IoMT): Wearable fitness monitors, remote patient cardiac telemetry, smart insulin pumps.
   • Industrial IoT (IIoT): Predictive machine maintenance, supply chain asset tracking.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The Perception layer is the foundation of IoT that directly interacts with the physical environment via sensors and actuators.
• Edge computing processes data NEAR the source to minimize latency and bandwidth consumption.
        """.trimIndent(),
        colorHex = 0xFF6366F1,
        initialLikes = 85
    ),
    ShortNote(
        id = "tech-u5",
        subjectId = "c8",
        subject = "Emerging Technologies",
        unit = "Unit 5",
        title = "Cloud Computing, Blockchain & Cybersecurity Essentials",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Modern digital transformation relies on reliable cloud infrastructure, decentralized blockchain protocols, and robust cybersecurity defenses to ensure operational integrity and data protection.

🔍 DETAILED BREAKDOWN
1. Cloud Computing Service & Deployment Models:
   • Service Models:
     - IaaS (Infrastructure as a Service): Provides virtualized computing resources, servers, storage, and networking. User manages OS, apps, and data (e.g., AWS EC2, Google Compute Engine, Microsoft Azure VMs).
     - PaaS (Platform as a Service): Provides hardware and software tools over the internet for app development. User manages only code and data (e.g., Google App Engine, Heroku, AWS Elastic Beanstalk).
     - SaaS (Software as a Service): Complete ready-to-use software delivered over web browser. Provider manages everything (e.g., Gmail, Google Drive, Microsoft 365).
   • Deployment Models: Public Cloud (multi-tenant, accessible to anyone), Private Cloud (single organization exclusive use), Hybrid Cloud (combining public and private), Community Cloud (shared among specific institutions).

2. Blockchain Technology:
   • Definition: A decentralized, distributed, immutable public ledger that records transactions across many computers without a central intermediary.
   • Core Components:
     - Blocks: Contain transaction data, timestamp, current hash, and Previous Block Hash (linking them into an unbreakable chain).
     - Cryptographic Hash (SHA-256): One-way cryptographic function generating unique fixed 256-bit output. Changing any byte in a block invalidates all subsequent hashes.
     - Consensus Mechanisms: How nodes agree on valid ledger state:
       * Proof of Work (PoW): Solves computationally heavy puzzles (high energy; Bitcoin).
       * Proof of Stake (PoS): Validates based on coins held/staked (energy-efficient; Ethereum 2.0).
     - Smart Contracts: Self-executing digital contracts with terms directly written into code lines.

3. Cybersecurity Foundations & The CIA Triad:
   • Confidentiality: Ensuring sensitive data is accessible ONLY to authorized individuals (encryption, access control).
   • Integrity: Guaranteeing data is accurate, authentic, and protected against unauthorized modification (checksums, hashing, digital signatures).
   • Availability: Ensuring systems and data are accessible to authorized users when needed (redundancy, backup, DDoS mitigation).
   • Common Cyber Threats: Phishing (fraudulent emails), Ransomware (malware encrypting user data for extortion), Distributed Denial of Service (DDoS - overwhelming servers with fake traffic), Man-in-the-Middle (MITM).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The CIA Triad stands for Confidentiality, Integrity, and Availability.
• In Blockchain, each block contains the cryptographic hash of the PREVIOUS block, guaranteeing tamper-proof immutability.
        """.trimIndent(),
        colorHex = 0xFF6366F1,
        initialLikes = 97
    ),

    // =========================================================================
    // 9. MORAL & CIVICS EDUCATION (c9, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "civic-u1",
        subjectId = "c9",
        subject = "Moral and Civics Education",
        unit = "Unit 1",
        title = "Understanding Civics, Ethics, Morality & Democratic Citizenship",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Civics and Ethics educate citizens regarding their constitutional rights, public responsibilities, civic virtues, and ethical dilemmas, nurturing competent democratic participation within a multi-cultural society.

🔍 DETAILED BREAKDOWN
1. Conceptual Clarifications:
   • Civics: The systematic study of rights, duties, and responsibilities of citizens in a political community, and the structure and function of government.
   • Citizen: A legal member of a sovereign state entitled to constitutional rights and bound by civic duties.
   • Citizenship: The legal, political, and moral status that binds an individual to a sovereign state.
   • Morality vs Ethics:
     - Morality: Practical, culture-bound, or personal beliefs and customs regarding right vs wrong conduct.
     - Ethics: The formal, systematic philosophical study and critical examination of moral standards and values.

2. Dimensions of Civic Engagement:
   • Political Dimension: Exercising voting rights, participating in political debates, holding elected officials accountable, running for public office.
   • Civil Dimension: Participating in civil society organizations, community associations, voluntarism, and protecting collective goods.
   • Socio-Economic Dimension: Productive labor, paying legitimate taxes, fighting corruption, protecting environmental resources.

3. Core Goals of Civic & Ethical Education:
   • Building a culture of constitutional democracy and the rule of law.
   • Fostering national consensus, tolerance, mutual respect, and peaceful coexistence in diverse multi-ethnic societies.
   • Developing active, knowledgeable, critical, and responsible citizens capable of constructive public engagement.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Morality provides practical social guidelines; Ethics provides the philosophical critique and rational defense of those guidelines.
• Rights are not absolute; they are accompanied by corresponding civic responsibilities (e.g., freedom of speech does not allow hate speech or defamation).
        """.trimIndent(),
        colorHex = 0xFFE11D48,
        initialLikes = 79
    ),
    ShortNote(
        id = "civic-u2",
        subjectId = "c9",
        subject = "Moral and Civics Education",
        unit = "Unit 2",
        title = "Ethical Theories (Teleological, Deontological & Virtue Ethics)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Normative ethics offers systematic frameworks for evaluating moral actions, classifying them primarily into teleological (consequentialist), deontological (duty-based), and virtue-based ethical theories.

🔍 DETAILED BREAKDOWN
1. Teleological (Consequentialist) Theories:
   • Asserts that the moral rightness or wrongness of an action is determined SOLELY by its consequences or outcomes (the ends justify the means).
   • Utilitarianism (Jeremy Bentham & John Stuart Mill):
     - Principle of Utility: Actions are right in proportion as they tend to promote the greatest happiness for the greatest number of people.
     - Bentham's Quantitative Hedonism: Focused on quantity of pleasure measured by the Hedonic Calculus (intensity, duration, certainty).
     - Mill's Qualitative Hedonism: Differentiated higher intellectual pleasures from lower bodily pleasures ("Better to be Socrates dissatisfied than a fool satisfied").
     - Act Utilitarianism (evaluates each specific act) vs Rule Utilitarianism (follows rules that generally maximize utility).
   • Ethical Egoism: Asserts that individuals ought morally to act in their own rational long-term self-interest.

2. Deontological (Duty-Based) Ethics (Immanuel Kant):
   • Asserts that actions are intrinsically right or wrong based on moral duty, regardless of consequences.
   • The Good Will: The only thing good without qualification; acting purely out of reverence for moral law.
   • The Categorical Imperative:
     - First Formulation (Universal Law): "Act only according to that maxim whereby you can at the same time will that it should become a universal law."
     - Second Formulation (End in Itself): "Act in such a way that you treat humanity, whether in your own person or in that of another, always as an end and never merely as a means."
   • Strict prohibition against lying, even when lying might produce favorable short-term consequences.

3. Virtue Ethics (Aristotle):
   • Focuses on moral character and what kind of person one should become, rather than isolated rules or consequences.
   • Telos (Ultimate Goal): Eudaimonia (human flourishing, living well, realizing rational human potential).
   • The Doctrine of the Mean (Golden Mean): Moral virtue is the intermediate balance between two vices — deficiency and excess (e.g., Courage is the golden mean between Cowardice [deficiency] and Rashness [excess]).
   • Phronesis: Practical wisdom acquired through life experience and habitual practice.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Utilitarianism judges an action by its CONSEQUENCES; Deontology judges an action by MOTIVE and DUTY.
• Under Kant's second formulation of the Categorical Imperative, using people merely as tools (means to an end) is strictly immoral.
        """.trimIndent(),
        colorHex = 0xFFE11D48,
        initialLikes = 88
    ),
    ShortNote(
        id = "civic-u3",
        subjectId = "c9",
        subject = "Moral and Civics Education",
        unit = "Unit 3",
        title = "State, Government, Sovereignty & Citizenship Acquisition",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The State is the fundamental political organization of human society. Understanding its constitutive elements, functions, forms of government, and principles of citizenship acquisition is essential for constitutional governance.

🔍 DETAILED BREAKDOWN
1. Four Essential Elements of a State:
   • 1. Defined Population: A permanent aggregate of human beings residing within state boundaries.
   • 2. Defined Territory: A demarcated geographical space including land, inland waters, territorial airspace, and subsoil.
   • 3. Sovereign Government: The administrative machinery exercising political authority.
   • 4. Sovereignty: Supreme independent political authority, divided into:
     - Internal Sovereignty: Ultimate supreme lawmaking authority over domestic territory and citizens.
     - External Sovereignty: Complete independence from foreign control and recognized legal equality among sovereign states.

2. State vs Government:
   • The State is permanent, abstract, and encompasses the whole community; Government is temporary, concrete, and is merely the administrative organ of the state.

3. Major Systems of Government:
   • Parliamentary System (Ethiopia, UK, India):
     - Executive branch is chosen from and accountable to the legislative branch.
     - Head of State (President/Monarch, ceremonial role) is distinct from Head of Government (Prime Minister, executive power).
   • Presidential System (USA):
     - Clear separation of powers between executive and legislative branches.
     - President is directly elected and serves simultaneously as Head of State and Head of Government.
   • Unitary vs Federal State Structure:
     - Unitary: Central government holds supreme authority, delegating administrative powers to sub-units.
     - Federal: Power is constitutionally shared and divided between central government and regional member states (e.g., Ethiopia, USA, Nigeria).

4. Methods of Acquiring and Losing Citizenship:
   • Acquisition by Birth:
     - Jus Sanguinis ("Right of Blood"): Citizenship determined by the citizenship of parents (the primary principle applied in Ethiopia).
     - Jus Soli ("Right of Soil"): Citizenship determined by place/territory of birth (e.g., USA, Canada).
   • Acquisition by Naturalization (Law): Legal grant through marriage, prolonged legal residency, adoption, or special national contribution.
   • Loss of Citizenship: Renunciation (voluntary giving up), Deprivation (revocation for treason/fraud), Substitution.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Ethiopia's nationality law primarily adheres to Jus Sanguinis: an infant born to an Ethiopian father or mother anywhere in the world is Ethiopian.
• In a Parliamentary system like Ethiopia, the Prime Minister is the effective Head of Government, while the President is ceremonial Head of State.
        """.trimIndent(),
        colorHex = 0xFFE11D48,
        initialLikes = 84
    ),
    ShortNote(
        id = "civic-u4",
        subjectId = "c9",
        subject = "Moral and Civics Education",
        unit = "Unit 4",
        title = "Constitution, Constitutionalism & Ethiopian Constitutional Evolution",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
A constitution is the fundamental supreme law of a state defining political structures, government powers, and human rights. Constitutionalism is the political doctrine limiting arbitrary government power through the rule of law.

🔍 DETAILED BREAKDOWN
1. Constitution and Constitutionalism:
   • Constitution: The organic supreme legal document governing the state, establishing branches of government, distributing authority, and guaranteeing citizen rights.
   • Constitutionalism: The political philosophy that government authority must be legally constrained by institutional checks and balances, separation of powers, and judicial oversight. A country can have a constitution WITHOUT practicing constitutionalism (nominal/facade constitutions in dictatorships).

2. Classifications of Constitutions:
   • Written (Codified in single document, e.g., Ethiopia, USA) vs Unwritten (Uncodified conventions and statutes, e.g., UK).
   • Rigid (Difficult amendment process requiring supermajorities) vs Flexible (Amended through ordinary legislative procedures).

3. Constitutional Evolution in Ethiopia:
   • 1931 Constitution: First written constitution in Ethiopian history; granted by Emperor Haile Selassie I; absolute imperial sovereignty; established bicameral advisory parliament.
   • 1955 Revised Constitution: Maintained imperial absolutism; introduced elected Chamber of Deputies and nominal bill of rights; reinforced divine right of the emperor.
   • 1987 PDRE Constitution: Socialist constitution under Derg military regime; created People's Democratic Republic of Ethiopia; single-party state (Workers' Party of Ethiopia); nominal autonomy for nationalities.
   • 1995 FDRE Constitution: Current constitution; established Federal Democratic Republic of Ethiopia; based on self-determination of Nations, Nationalities, and Peoples (Art. 39).

4. Fundamental Principles of the 1995 FDRE Constitution:
   • Article 8: Sovereignty of the Nations, Nationalities, and Peoples of Ethiopia.
   • Article 9: Supremacy of the Constitution (any law or practice contrary to it is null and void).
   • Article 10: Sanctity of Human and Democratic Rights.
   • Article 11: Separation of State and Religion (Secularism: state shall not interfere in religion; religion shall not interfere in state affairs).
   • Article 12: Transparency and Accountability of Government Conduct.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The 1931 Constitution was the FIRST written constitution in Ethiopia, but it centralized all sovereign power in the Emperor.
• Under Article 9 of the FDRE Constitution, the Constitution is the SUPREME law of the land; all customary practices, proclamations, or executive directives conflicting with it are invalid.
        """.trimIndent(),
        colorHex = 0xFFE11D48,
        initialLikes = 93
    ),
    ShortNote(
        id = "civic-u5",
        subjectId = "c9",
        subject = "Moral and Civics Education",
        unit = "Unit 5",
        title = "Human Rights Generations, Rule of Law & Good Governance",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Human rights are basic universal entitlements inherent to all human beings without discrimination. Good governance and the rule of law create the necessary environment for protecting rights and fostering sustainable development.

🔍 DETAILED BREAKDOWN
1. Nature & Characteristics of Human Rights:
   • Universal (belong to everyone everywhere), Inalienable (cannot be taken away or surrendered), Indivisible (all rights hold equal importance), Interdependent (fulfillment of one depends on others).

2. The Three Generations of Human Rights (Karel Vasak):
   • 1st Generation (Civil and Political Rights - "Liberty"):
     - Rooted in the American and French revolutions.
     - Negative rights: Require the state to abstain from arbitrary interference.
     - Examples: Right to life, liberty, fair trial, freedom of speech, freedom of religion, protection against torture.
     - Codified in the International Covenant on Civil and Political Rights (ICCPR).
   • 2nd Generation (Economic, Social, and Cultural Rights - "Equality"):
     - Rooted in socialist movements and industrial welfare demands.
     - Positive rights: Require proactive state intervention and resource allocation.
     - Examples: Right to education, healthcare, adequate housing, fair working conditions, social security.
     - Codified in the International Covenant on Economic, Social, and Cultural Rights (ICESCR).
   • 3rd Generation (Solidarity / Group Rights - "Fraternity"):
     - Collective rights belonging to communities and peoples.
     - Examples: Right to peace, clean environment, sustainable development, self-determination.

3. Rule of Law & Core Pillars of Good Governance:
   • Rule of Law: Absolute supremacy of regular law over arbitrary power; equality before the law; protection of fundamental rights through independent judiciary.
   • Eight Major Pillars of Good Governance (UN/World Bank):
     1. Participation: Active involvement of all citizens in decision-making.
     2. Rule of Law: Fair legal frameworks enforced impartially.
     3. Transparency: Free flow of public information and accessible decisions.
     4. Responsiveness: Serving stakeholders within a reasonable timeframe.
     5. Consensus Orientation: Mediating diverse societal interests for broad consensus.
     6. Equity and Inclusiveness: Ensuring marginalized groups have opportunities.
     7. Effectiveness & Efficiency: Producing results meeting societal needs while making optimal use of resources.
     8. Accountability: Public and private institutions are answerable to the public.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• 1st Generation rights are primarily NEGATIVE (state refrains from infringing); 2nd Generation rights are POSITIVE (state must provide resources).
• Good governance requires BOTH accountability (being answerable) and transparency (public access to decisions and records).
        """.trimIndent(),
        colorHex = 0xFFE11D48,
        initialLikes = 86
    ),

    // =========================================================================
    // 10. GENERAL LAW (c10, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "law-u1",
        subjectId = "c10",
        subject = "General Law",
        unit = "Unit 1",
        title = "Nature, Functions & Major Classifications of Law",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Law is a system of binding rules enacted or recognized by sovereign authority to govern human conduct and maintained through state sanctions. It provides order, resolves disputes, and safeguards individual and social interests.

🔍 DETAILED BREAKDOWN
1. Essential Features & Functions of Law:
   • Features: Normative (prescribes what ought to be done), General (applies to classes of persons, not specific individuals), Enforceable (backed by coercive state sanctions).
   • Functions: Maintaining social order and stability, peaceful dispute settlement, protecting individual freedoms, facilitating commercial enterprise, promoting social justice.

2. Major Classifications of Law:
   • Public Law vs Private Law:
     - Public Law: Governs relationships between the state and individuals (Constitutional Law, Administrative Law, Criminal Law).
     - Private Law: Governs relationships between private legal persons (Civil Law, Contract Law, Tort Law, Commercial Law, Family Law).
   • Substantive Law vs Procedural Law:
     - Substantive Law: Defines, creates, and regulates legal rights, duties, liabilities, and crimes (e.g., Penal Code, Civil Code).
     - Procedural Law: Prescribes the methods, mechanisms, and rules of court through which substantive rights are enforced (e.g., Civil Procedure Code, Criminal Procedure Code).
   • Criminal Law vs Civil Law:
     - Criminal Law: Offenses against society; prosecuted by Public Prosecutor; standard of proof is "Beyond a Reasonable Doubt"; remedy is punishment (imprisonment, fines).
     - Civil Law: Wrongs against private individuals (plaintiff vs defendant); standard of proof is "Preponderance of Evidence"; remedy is compensation/damages or restitution.

3. Global Legal Traditions:
   • Civil Law System (Continental): Originated from Roman Law (Corpus Juris Civilis); heavily codified into comprehensive statutes; judges apply written codes; non-precedential (e.g., France, Germany).
   • Common Law System (Anglo-American): Originated in England; judge-made law through judicial precedent under the doctrine of Stare Decisis (binding precedents); adversarial trial system (e.g., UK, USA).
   • Ethiopian Legal System: Mixed legal system; heavily codified along Civil Law lines, but incorporates precedent through the binding rulings of the Federal Supreme Court Cassation Division.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Substantive law defines WHAT rights and duties exist; Procedural law dictates HOW those rights are litigated and enforced in court.
• In criminal cases, burden of proof is "beyond reasonable doubt"; in civil suits, it is "preponderance of evidence".
        """.trimIndent(),
        colorHex = 0xFF475569,
        initialLikes = 77
    ),
    ShortNote(
        id = "law-u2",
        subjectId = "c10",
        subject = "General Law",
        unit = "Unit 2",
        title = "Sources of Law & Hierarchy in the Ethiopian Legal System",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Sources of law are the authoritative origins and manifestations from which legally enforceable rules derive. In Ethiopia, laws follow a strict constitutional hierarchy.

🔍 DETAILED BREAKDOWN
1. Hierarchy of Laws in the Federal Democratic Republic of Ethiopia:
   • 1. FDRE Constitution (1995):
     - Supreme law of the land (Article 9). Any law, customary practice, or decision of an organ of state that contravenes it has no legal effect.
   • 2. Ratified International Treaties:
     - International agreements ratified by Ethiopia form an integral part of the law of the land (Article 9(4)).
     - Human rights provisions must be interpreted in conformity with UDHR, ICCPR, and international covenants (Article 13(2)).
   • 3. Federal Proclamations:
     - Primary legislation enacted exclusively by the House of Peoples' Representatives (HoPR).
   • 4. Council of Ministers Regulations:
     - Subordinate (delegated) legislation enacted by the Council of Ministers based on explicit authority granted in a primary proclamation.
   • 5. Ministerial Directives:
     - Tertiary administrative rules issued by individual ministries or government authorities for daily implementation.
   • Regional State Laws: Within their constitutionally designated spheres of competence, Regional State Constitutions, Proclamations, and Regulations have authoritative force.

2. Non-Statutory & Secondary Sources of Law:
   • Customary Law: Recognized under Article 34(5) of the Constitution for personal and family disputes, provided both disputing parties consent.
   • Judicial Precedent: While historically non-precedential, Federal Court Proclamation No. 454/2005 establishes that legal interpretations rendered by the Cassation Division of the Federal Supreme Court with not less than five judges are BINDING on all federal and regional courts.

3. Canons of Statutory Interpretation:
   • Literal Rule: Words are interpreted according to their plain, literal, and ordinary grammatical meaning.
   • Golden Rule: If literal meaning leads to manifest absurdity or repugnancy, judges modify the language to achieve legislative intent.
   • Mischief Rule (Purposive Approach): Interprets statute based on the defect or "mischief" the original law was intended to cure.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Memorize the Ethiopian hierarchy: Constitution -> International Treaties -> Proclamations (HoPR) -> Regulations (Council of Ministers) -> Directives (Ministries).
• The Federal Supreme Court Cassation Division decisions are legally binding on ALL courts in Ethiopia (Proc. No. 454/2005).
        """.trimIndent(),
        colorHex = 0xFF475569,
        initialLikes = 85
    ),
    ShortNote(
        id = "law-u3",
        subjectId = "c10",
        subject = "General Law",
        unit = "Unit 3",
        title = "Court Structure, Judicial Power & Alternative Dispute Resolution",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The Ethiopian judicial architecture is structured into a dual court system reflecting federalism. Alongside formal court litigation, Alternative Dispute Resolution (ADR) provides efficient, flexible dispute settlement.

🔍 DETAILED BREAKDOWN
1. Dual Judicial System of Ethiopia:
   • Federal Courts and Regional State Courts operate in parallel.
   • Three-Tier Hierarchy of Federal Courts:
     1. Federal First Instance Court: Original jurisdiction over basic civil and criminal federal matters.
     2. Federal High Court: Hears appeals from First Instance, and has original jurisdiction over high-stakes federal cases (treason, anti-terrorism, major economic crimes).
     3. Federal Supreme Court: Highest appellate authority in the federal system.
   • The Cassation Division (Federal Supreme Court):
     - Final review authority to correct "fundamental errors of law" in any final court judgment across federal and regional levels.

2. Judicial Power & Constitutional Interpretation:
   • In Ethiopia, judicial power is vested in courts (FDRE Constitution Art. 79).
   • Uniqueness of Constitutional Interpretation in Ethiopia:
     - Courts do NOT possess power to invalidate laws on constitutional grounds!
     - Constitutional interpretation is vested exclusively in the House of Federation (HoF - upper legislative house representing nationalities), assisted by the Council of Constitutional Inquiry (CCI) (Articles 62 & 83).

3. Alternative Dispute Resolution (ADR):
   • Methods:
     - Negotiation: Disputing parties discuss directly to reach mutual compromise without third-party intervention.
     - Mediation: A neutral third party (mediator) facilitates communication and guides negotiations, but does NOT impose a binding decision.
     - Conciliation: Similar to mediation, but conciliator may propose non-binding compromise settlement proposals.
     - Arbitration: Disputing parties submit dispute to private neutral third party (arbitrator), who issues a legally binding decision (Arbitral Award).
   • Advantages of ADR over Litigation: Cost-effective, faster proceedings, confidential, preserves business and personal relationships, flexible scheduling.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• A major exam trap: In Ethiopia, the SUPREME COURT does NOT interpret the Constitution. The HOUSE OF FEDERATION (HoF) holds exclusive constitutional interpretation authority!
• In arbitration, the arbitrator's decision (award) is BINDING; in mediation, the mediator's advice is non-binding.
        """.trimIndent(),
        colorHex = 0xFF475569,
        initialLikes = 89
    ),
    ShortNote(
        id = "law-u4",
        subjectId = "c10",
        subject = "General Law",
        unit = "Unit 4",
        title = "Law of Persons, Legal Personality & Incapacity",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The Law of Persons regulates who can hold legal rights and duties. It differentiates natural human beings from artificial corporate entities and outlines rules of legal capacity and protection of vulnerable persons.

🔍 DETAILED BREAKDOWN
1. Physical (Natural) Persons & Legal Personality:
   • Commencement of Personality: Under the Ethiopian Civil Code (Art. 1), the human person is the subject of rights from birth to death.
   • Rights of the Conceived Child (Nasciturus Doctrine, Art. 2): A child merely conceived is considered born whenever its personal interest so requires (e.g., succession/inheritance), provided it is subsequently born alive and viable.
   • Viability (Art. 4): A child is viable if it lives for at least 48 hours following live birth, or if medical evidence proves it was capable of survival.
   • Termination of Personality: Legal personality terminates upon biological death, or upon legal Declaration of Absence by a competent court when a person disappears without news.

2. Juridical (Artificial / Legal) Persons:
   • Entities created by law that possess independent legal personality distinct from their human owners or members:
     - Public Juridical Persons: State organs, municipalities, public universities.
     - Private Juridical Persons: Share companies, private limited companies (PLC), cooperative societies, NGOs.
   • Attributes: Can own property, enter binding contracts, sue and be sued in its own corporate name, perpetual succession.

3. Legal Capacity & Incapacity:
   • Capacity to Enjoy Rights vs Capacity to Exercise Rights:
     - Every human being has the capacity to ENJOY rights (passive capacity).
     - However, certain persons lack the capacity to EXERCISE those rights (active capacity to perform legal acts independently).
   • Categories of Legally Incapacitated Persons:
     - Minors: Persons under 18 years of age (governed by parents/tutors).
     - Judicially Interdicted Persons: Adults suffering from severe mental illness or impairment, declared incapacitated by court judgment.
     - Legally Interdicted Persons: Persons disqualified from exercising legal rights as a consequence of criminal conviction.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The conceived fetus enjoys legal rights conditionally: it MUST be born ALIVE and VIABLE (surviving 48 hours or proven medically viable).
• A minor is any person who has not attained the full age of 18 years under Ethiopian law.
        """.trimIndent(),
        colorHex = 0xFF475569,
        initialLikes = 82
    ),
    ShortNote(
        id = "law-u5",
        subjectId = "c10",
        subject = "General Law",
        unit = "Unit 5",
        title = "Law of Contracts, Validity Requirements & Breach Remedies",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Contracts are the foundation of civil and commercial interaction. Ethiopian Civil Code defines a contract as an agreement whereby two or more persons establish, modify, or extinguish legal obligations (Art. 1675).

🔍 DETAILED BREAKDOWN
1. Essential Elements for Contractual Validity (Art. 1678):
   • 1. Legal Capacity: The parties must be legally capable of contracting (not incapacitated minors or interdicted persons).
   • 2. Free and Enlightened Consent: Mutual agreement without vices of consent:
     - Mistake (Error): Must relate to an essential element of the contract.
     - Fraud (Dolo): Intentional deceit inducing contract formation.
     - Duress (Violence): Compelling consent through credible threats of serious and imminent harm.
   • 3. Lawful, Possible, and Determinate Object: The obligation must be legally permissible, physically possible, moral, and clearly defined.
   • 4. Prescribed Form (Where Required by Law): Most contracts are consensual (no special form required); however, contracts involving immovable property (land/buildings), contracts with public administrations, or guarantees MUST be in writing and registered.

2. Performance of Contracts:
   • Principle of Sanctity of Contracts (Pacta Sunt Servanda, Art. 1731): Contracts validly formed are binding on parties as if they were law.
   • Good Faith: Obligations must be performed in good faith according to customary commercial fairness.

3. Remedies for Non-Performance (Breach of Contract):
   • When a party fails to fulfill their obligation, the creditor may pursue:
     1. Forced (Specific) Performance: Compelling the breaching party by court order to perform the exact promised act (where possible without violating personal freedom).
     2. Cancellation: Dissolving the contract and returning parties to pre-contract status quo ante.
     3. Damages (Compensation): Financial compensation for actual loss suffered and gain prevented (lucrum cessans).
   • Force Majeure (Art. 1792): An unforeseeable, insurmountable external event that completely prevents performance relieves the party of contractual liability.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Vices of consent (Mistake, Fraud, Duress) render a contract RELATIVELY invalid (voidable at the option of the victim), NOT automatically void ab initio.
• Pacta Sunt Servanda means agreements legally made must be respected and enforced as the law of the parties.
        """.trimIndent(),
        colorHex = 0xFF475569,
        initialLikes = 91
    ),

    // =========================================================================
    // 11. INTRODUCTION TO ECONOMICS (c11, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "econ-u1",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 1",
        title = "Nature of Economics, Scarcity, PPF & Economic Systems",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Economics is the social science studying how individuals and societies allocate scarce productive resources among competing unlimited human wants to maximize societal welfare. It examines resource scarcity, opportunity costs, production possibilities, methodological divisions, and contrasting institutional economic systems.

🔍 DETAILED BREAKDOWN
1. Definition & Nature of Economics:
   • Etymology: Derived from ancient Greek "oikonomia" ("oikos" = household, "nemein" = manage), meaning household management.
   • Formal Definition: The study of how scarce productive resources are allocated to satisfy unlimited human material wants.
   • The Fundamental Economic Problem (Scarcity):
     - Scarcity: Human material desires are virtually infinite and boundless, whereas productive resources are finite and scarce.
     - Scarcity is universal (affects both rich and poor nations), relative (wants exceed means), and persistent.
   • Four Factors of Production (Productive Inputs):
     1. Land: Natural resources, agricultural soil, water bodies, minerals, forests (Reward: Rent).
     2. Labor: Physical and mental human exertion used in production (Reward: Wages).
     3. Capital: Man-made physical tools, machinery, equipment, buildings, infrastructure used to produce other goods (Reward: Interest). Note: Money is financial capital, NOT an economic factor of production!
     4. Entrepreneurship: Managerial initiative, risk-taking, coordination, and technological innovation (Reward: Profit).
   • Choice & Opportunity Cost:
     - Scarcity forces choice; choice inevitably involves sacrifice.
     - Opportunity Cost: The value of the next best alternative sacrificed or forgone when making a decision.

2. Scope & Methodologies of Economics:
   • Microeconomics vs. Macroeconomics:
     - Microeconomics: Studies individual decision-making agents (households, individual consumers, workers, firms, single product markets) and determination of relative prices.
     - Macroeconomics: Studies the aggregate performance of the economy as a whole (Gross Domestic Product, inflation, national unemployment, fiscal and monetary policies, balance of payments).
   • Positive Economics vs. Normative Economics:
     - Positive Economics: Objective, fact-based analysis of "what is", "what was", or "what will be". Deals with testable and verifiable statements free of personal value judgments.
     - Normative Economics: Subjective, value-laden prescriptions of "what ought to be" or "what should be". Cannot be empirically verified or refuted by data.
   • The Ceteris Paribus Assumption: "Other things being equal" — isolating the relationship between two specific variables while holding all other external factors constant.
   • Common Pitfalls in Economic Reasoning:
     - Fallacy of Composition: Erroneous assumption that what is true for an individual part is automatically true for the whole (e.g., paradox of thrift).
     - Post Hoc Fallacy (Post hoc ergo propter hoc): Confusing mere temporal sequence or correlation with genuine causation.

3. The Production Possibility Frontier (PPF):
   • Definition: A graphical model illustrating the maximum combinations of two goods or services an economy can produce under conditions of full employment of resources and fixed technology.
   • Core Assumptions: Only two goods produced, resources are fixed in quantity and quality, technology is constant, resources are utilized with full productive efficiency.
   • Points on the PPF Graph:
     - Points ON the curve: Productively efficient (full employment of productive resources).
     - Points INSIDE the curve: Inefficient (unemployment, underutilization, or misallocation of resources).
     - Points OUTSIDE the curve: Currently unattainable given current resources and technology (attainable only through economic growth).
   • Shape & Slope of the PPF:
     - Concave to the origin (bowed outward) due to the Law of Increasing Opportunity Cost: Resources are specialized and not equally adaptable across different production lines.
     - Slope of PPF = Marginal Rate of Transformation (MRT = -ΔY / ΔX), which mathematically measures the Opportunity Cost of good X.
     - Straight-line linear PPF occurs ONLY when opportunity cost is constant.
   • Shifts of the PPF (Economic Growth):
     - Parallel outward shift: General technological improvement, increase in total labor force, or capital accumulation.
     - Asymmetrical / Biased shift: Technological advance affecting only one specific sector.

4. Basic Economic Questions & Economic Systems:
   • The Three Fundamental Economic Questions:
     1. What to produce? (Determining the composition and quantity of goods).
     2. How to produce? (Selecting combination of resources: labor-intensive vs. capital-intensive technology).
     3. For whom to produce? (Distributing generated national output and income).
   • Four Major Economic Systems:
     - Traditional Economy: Economic choices determined by custom, tribal heritage, religion, and barter trade.
     - Command (Socialist/Planned) Economy: Productive resources owned by the state; central planning authority dictates what, how, and for whom to produce (e.g., historical USSR, Derg regime in Ethiopia). Lacks price mechanism and consumer sovereignty.
     - Free Market (Capitalist) Economy: Private resource ownership; resource allocation guided entirely by the price mechanism and the "invisible hand" (Adam Smith); motivated by private profit; consumer sovereignty and laissez-faire.
     - Mixed Economy: Combines private market forces with public state ownership, regulation, and welfare intervention (the model practiced in modern Ethiopia).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The slope of the PPF measures Opportunity Cost (Marginal Rate of Transformation - MRT). A concave PPF reflects INCREASING opportunity cost.
• Positive statements are verifiable facts ("Inflation in Ethiopia is 25%"); Normative statements are value judgments ("The government SHOULD reduce fuel prices").
• Money is NOT an economic factor of production; only physical capital (machinery, tools, factories) counts as capital.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 88
    ),
    ShortNote(
        id = "econ-u2",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 2",
        title = "Demand, Supply, Market Equilibrium & Elasticity Analysis",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Markets determine prices and output through the interaction of buyers (Demand) and sellers (Supply). Understanding market equilibrium, disequilibrium corrections, government price interventions (ceilings and floors), and elasticity metrics is essential for microeconomic analysis.

🔍 DETAILED BREAKDOWN
1. Theory of Demand:
   • Definition: The willingness and ability of consumers to purchase various quantities of a good at alternative prices during a given time period.
   • Law of Demand: Other things held constant (ceteris paribus), as price rises, quantity demanded falls; as price falls, quantity demanded rises (inverse relationship).
   • Three Reasons for the Downward-Sloping Demand Curve:
     1. Income Effect: A lower price increases consumers' real purchasing power, enabling them to buy more.
     2. Substitution Effect: When the price of a good falls, it becomes relatively cheaper compared to alternatives, encouraging substitution.
     3. Law of Diminishing Marginal Utility: Additional satisfaction declines with each successive unit consumed, so buyers purchase more only at lower prices.
   • Demand Function: Qd = a - bP (where -b is the slope coefficient).
   • Change in Quantity Demanded vs. Change in Demand:
     - Change in Quantity Demanded: Movement ALONG the existing demand curve caused EXCLUSIVELY by a change in the good's own price.
     - Change in Demand: SHIFT of the entire demand curve caused by non-price determinants:
       * Consumer Income: Normal goods (income rises -> demand shifts right); Inferior goods (income rises -> demand shifts left).
       * Prices of Related Goods: Substitutes (price of coffee rises -> tea demand shifts right); Complements (price of fuel rises -> car demand shifts left).
       * Tastes, preferences, consumer expectations of future prices, and number of buyers.

2. Theory of Supply:
   • Definition: The willingness and ability of producers to offer quantities of a good for sale at various prices.
   • Law of Supply: Other things being equal, as price rises, quantity supplied increases; as price falls, quantity supplied decreases (direct relationship).
   • Supply Function: Qs = c + dP.
   • Non-Price Supply Determinants (Shifts of Supply Curve):
     - Input/resource prices (wages, raw materials), technology level, taxes and subsidies, producer expectations, natural/weather conditions, number of competing firms.

3. Market Equilibrium & Price Dynamics:
   • Equilibrium Condition: Quantity Demanded equals Quantity Supplied: Qd = Qs. Market-clearing price (P*) and equilibrium quantity (Q*).
   • Market Disequilibrium:
     - Surplus (Excess Supply): Market price is ABOVE P* (Qs > Qd). Unsold inventory forces sellers to reduce prices until equilibrium is restored.
     - Shortage (Excess Demand): Market price is BELOW P* (Qd > Qs). Frustrated buyers bid prices upward until balance is regained.
   • Government Market Interventions:
     - Price Ceiling: Maximum legal price set BELOW equilibrium to protect consumers (e.g., rent control). Leads to chronic shortages, black markets, queuing, and deadweight loss.
     - Price Floor: Minimum legal price set ABOVE equilibrium to protect suppliers (e.g., minimum wage, agricultural price supports). Leads to chronic surpluses and government buy-back burdens.

4. Elasticity of Demand and Supply:
   • Price Elasticity of Demand (PED / ed):
     - Measures responsiveness of quantity demanded to a change in price: ed = (%ΔQd) / (%ΔP).
     - Midpoint (Arc) Formula: ed = [(Q2 - Q1) / ((Q1 + Q2)/2)] ÷ [(P2 - P1) / ((P1 + P2)/2)].
     - Point Elasticity: ed = (dQ/dP) × (P / Q).
     - Five Categories of Elasticity (|ed|):
       * Perfectly Inelastic (|ed| = 0): Vertical demand curve (e.g., essential insulin).
       * Inelastic (0 < |ed| < 1): %ΔQd < %ΔP (e.g., basic food, fuel, salt).
       * Unitary Elastic (|ed| = 1): %ΔQd = %ΔP.
       * Elastic (|ed| > 1): %ΔQd > %ΔP (e.g., luxury goods, goods with close substitutes).
       * Perfectly Elastic (|ed| = ∞): Horizontal demand curve (perfect competition).
   • Total Revenue (TR = P × Q) Test:
     - Inelastic Demand (|ed| < 1): Price and TR move in the SAME direction (raising price increases TR).
     - Elastic Demand (|ed| > 1): Price and TR move in OPPOSITE directions (cutting price increases TR).
     - Unitary Elastic (|ed| = 1): TR is maximized and unaffected by small price changes.
   • Cross-Price Elasticity of Demand (XED):
     - XED = (%ΔQd of X) / (%ΔP of Y).
     - XED > 0 (Positive): Substitute goods (e.g., tea & coffee).
     - XED < 0 (Negative): Complementary goods (e.g., cars & petrol).
     - XED = 0: Independent / unrelated goods.
   • Income Elasticity of Demand (YED):
     - YED = (%ΔQd) / (%ΔIncome).
     - YED > 0: Normal goods (YED > 1: Luxury; 0 < YED <= 1: Necessity).
     - YED < 0: Inferior goods (e.g., second-hand clothes, low-grade staples).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The sign is diagnostic! Cross-price elasticity: Positive = Substitutes, Negative = Complements. Income elasticity: Positive = Normal, Negative = Inferior.
• Price Ceiling is only effective/binding when set BELOW equilibrium (causes Shortage); Price Floor is only binding when set ABOVE equilibrium (causes Surplus).
• Total Revenue Test: If a producer faces elastic demand, they should LOWER price to increase revenue; if inelastic, they should RAISE price.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 94
    ),
    ShortNote(
        id = "econ-u3",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 3",
        title = "Theory of Consumer Behavior: Cardinal & Ordinal Utility Analysis",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Consumer behavior explains how rational individuals allocate limited money income among diverse goods and services to achieve maximum satisfaction (utility). Economics models this behavior through two major paradigms: the Cardinal Utility Approach (Marshallian) and the Ordinal Utility Approach (Indifference Curve analysis).

🔍 DETAILED BREAKDOWN
1. Cardinal Utility Approach (Marshallian):
   • Core Assumptions: Utility is quantitatively measurable in monetary or psychological units ("utils"), consumer is rational, constant marginal utility of money, diminishing marginal utility.
   • Total Utility (TU): The cumulative total satisfaction derived from consuming a specific quantity of a commodity.
   • Marginal Utility (MU): The additional satisfaction gained from consuming one additional unit of a commodity: MU = ΔTU / ΔQ = dTU / dQ.
   • Law of Diminishing Marginal Utility (Gossen's First Law):
     - As a consumer consumes more units of a good within a given period, the satisfaction derived from each successive unit decreases.
   • Relationships Between Total Utility (TU) and Marginal Utility (MU):
     - When MU > 0, Total Utility (TU) is increasing at a decreasing rate.
     - When MU = 0, Total Utility (TU) reaches its ABSOLUTE MAXIMUM (the saturation / satiety point).
     - When MU < 0, Total Utility (TU) begins to decline (disutility).
     - Graphically, the downward-sloping portion of the positive MU curve constitutes the individual's demand curve.
   • Consumer Equilibrium under Cardinal Theory (Equi-Marginal Principle / Gossen's Second Law):
     - Single commodity: MUx = Px.
     - Multiple commodities: The consumer maximizes satisfaction when the marginal utility per dollar spent is equal across all goods:
       MUx / Px = MUy / Py = ... = MUn / Pn = MUm (Marginal utility of money)
       Subject to the budget constraint: (Px × X) + (Py × Y) = M.

2. Ordinal Utility Approach (Hicks-Allen / Indifference Curve Analysis):
   • Core Axioms: Utility cannot be measured quantitatively; consumers can only rank bundles in order of preference (1st, 2nd, 3rd). Axioms of Completeness, Transitivity, and Non-Satiation ("more is preferred to less").
   • Indifference Curve (IC):
     - A curve connecting all combinations of two goods (X and Y) that yield the exact same level of total satisfaction to the consumer.
   • Four Fundamental Properties of Indifference Curves:
     1. Downward Sloping to the Right (Negative Slope): To obtain more of Good X while maintaining constant utility, the consumer must sacrifice some of Good Y.
     2. Convex to the Origin: Reflects the Law of Diminishing Marginal Rate of Substitution (MRS).
     3. Two Indifference Curves Cannot Intersect: Proved by the transitivity of preferences (intersection implies logical contradiction).
     4. Higher Indifference Curves Represent Higher Satisfaction: Due to the non-satiation axiom (higher curves contain more of both goods).
   • Marginal Rate of Substitution (MRSx,y):
     - The quantity of Good Y a consumer is willing to give up to acquire one additional unit of Good X while remaining on the same indifference curve:
     - MRSx,y = -ΔY / ΔX = MUx / MUy.
     - Diminishing MRS: As the consumer obtains more X, they value Y relatively more, willing to surrender fewer units of Y for additional X.
   • Special Indifference Curve Shapes:
     - Perfect Substitutes: Straight downward-sloping lines with constant MRS (linear IC).
     - Perfect Complements: L-shaped (right-angled) indifference curves (Leontief utility; e.g., left shoes and right shoes).

3. The Budget Line:
   • Represents all combinations of Good X and Good Y that a consumer can purchase by exhausting their entire money income (M).
   • Budget Equation: (Px × X) + (Py × Y) = M  ==>  Y = (M / Py) - (Px / Py) × X.
   • Geometric Features:
     - Vertical intercept = M / Py; Horizontal intercept = M / Px.
     - Slope of Budget Line = -Px / Py (the relative price ratio or economic exchange rate).
   • Shifts vs. Rotations of Budget Line:
     - Income change shifts budget line parallel outward (income increases) or inward (income decreases).
     - Price change of one good rotates the budget line around the axis of the unchanged good.

4. Consumer Equilibrium (Optimum) in Ordinal Theory:
   • Consumer maximizes satisfaction at the point of TANGENCY between the budget line and the highest attainable indifference curve.
   • Equilibrium Condition: Slope of Indifference Curve = Slope of Budget Line:
     MRSx,y = Px / Py  <==>  MUx / MUy = Px / Py  <==>  MUx / Px = MUy / Py.
   • Income Consumption Curve (ICC) & Engel Curve: Traces equilibrium points as income varies; derives Engel curve.
   • Price Consumption Curve (PCC) & Individual Demand Curve: Traces equilibrium points as the price of a good varies; derives the downward-sloping demand curve.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Satiety Point: Total Utility is MAXIMIZED when Marginal Utility equals ZERO (MU = 0).
• Consumer Optimum Tangency Rule: MRSx,y = Px / Py (or MUx / Px = MUy / Py).
• Indifference curve properties: Downward sloping, convex to origin, never cross, higher curve = higher satisfaction.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 92
    ),
    ShortNote(
        id = "econ-u4",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 4",
        title = "Theory of Production, Returns to Scale & Cost Dynamics",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Firms transform productive inputs into marketable outputs to maximize profit. Production theory explores input-output engineering relationships in the short run and long run, while cost theory translates physical production into monetary expenditures.

🔍 DETAILED BREAKDOWN
1. Theory of Production in the Short Run:
   • Production Function: Q = f(L, K) — technical relationship showing maximum output achievable from given inputs.
   • Time Horizons:
     - Short Run: Period in which at least one input is FIXED (typically capital K or plant size), while others are variable (labor L).
     - Long Run: Period long enough that ALL factors of production become VARIABLE.
   • Production Metrics:
     - Total Product (TP or Q): Total physical output produced.
     - Marginal Product of Labor (MPL): Additional output produced by employing one more unit of labor: MPL = ΔTP / ΔL = dTP / dL.
     - Average Product of Labor (APL): Output generated per unit of labor: APL = TP / L.
   • The Law of Diminishing Marginal Returns:
     - As successive units of a variable input (labor) are added to a fixed quantity of capital, a point is reached beyond which the marginal product of the variable input begins to decline.
   • The Three Stages of Production:
     - Stage I (Increasing Returns): From origin to where APL reaches its maximum (where MPL = APL). MPL > APL. Inefficient to stop here (fixed capital is underutilized).
     - Stage II (Diminishing Returns): From maximum APL to where MPL = 0 (where TP reaches its peak). Both APL and MPL are declining, but MPL > 0. RATIONAL OPERATING ZONE for profit-maximizing producers!
     - Stage III (Negative Returns): Beyond MPL = 0; MPL becomes negative (MPL < 0) and TP declines. Irrational zone (labor overcrowding).
   • Geometric Mathematical Relationships:
     - When MPL > APL, Average Product (APL) is rising.
     - When MPL = APL, Average Product (APL) is at its ABSOLUTE MAXIMUM.
     - When MPL < APL, Average Product (APL) is falling.
     - When MPL = 0, Total Product (TP) reaches its ABSOLUTE MAXIMUM.

2. Theory of Production in the Long Run:
   • Isoquant: A curve showing all combinations of labor (L) and capital (K) that produce the exact same level of output. Properties: Downward sloping, convex to origin, cannot intersect.
   • Marginal Rate of Technical Substitution (MRTS L,K): Slope of Isoquant = -ΔK / ΔL = MPL / MPK.
   • Isocost Line: Represents all input combinations that cost the firm the same total outlay: C = wL + rK; Slope = -w / r.
   • Producer Equilibrium (Cost Minimization / Output Maximization): Tangency between Isoquant and Isocost line:
     MRTS L,K = w / r  <==>  MPL / w = MPK / r.
   • Returns to Scale (Long Run with all inputs varying):
     - Increasing Returns to Scale (IRS): Output expands by a greater percentage than input expansion (e.g., doubling inputs triples output).
     - Constant Returns to Scale (CRS): Output expands by the exact same proportion as inputs.
     - Decreasing Returns to Scale (DRS): Output expands by a smaller proportion than inputs.

3. Theory of Costs:
   • Economic Costs = Explicit Costs (monetary payments for outsourced inputs) + Implicit Costs (opportunity costs of owner-supplied resources, including normal profit).
   • Economic Profit = Total Revenue - Economic Costs (Total Revenue - Explicit - Implicit).
   • Accounting Profit = Total Revenue - Explicit Costs (Accounting Profit is always greater than Economic Profit).
   • Short-Run Cost Structures:
     - Total Fixed Cost (TFC): Costs that do NOT vary with output (rent, insurance, plant depreciation). Graph is a horizontal line.
     - Total Variable Cost (TVC): Costs that vary directly with output volume (raw materials, wages). S-shaped due to diminishing returns.
     - Total Cost (TC): TC = TFC + TVC.
     - Average Fixed Cost (AFC): AFC = TFC / Q. Continually declines toward zero (rectangular hyperbola).
     - Average Variable Cost (AVC): AVC = TVC / Q. U-shaped.
     - Average Total Cost (ATC): ATC = TC / Q = AFC + AVC. U-shaped.
     - Marginal Cost (MC): Additional cost of producing one more unit of output: MC = ΔTC / ΔQ = ΔTVC / ΔQ = w / MPL.
   • Critical Geometric Relationships:
     - The Marginal Cost (MC) curve intersects BOTH the AVC and ATC curves at their ABSOLUTE MINIMUM points!
     - When MC < ATC, ATC is falling; when MC > ATC, ATC is rising.
     - The vertical distance between ATC and AVC equals AFC, which narrows as output expands.

4. Long-Run Costs & Economies of Scale:
   • Long-Run Average Cost (LRAC): The "envelope curve" encompassing all short-run ATC curves.
   • Economies of Scale: Declining LRAC as plant scale expands (due to labor specialization, bulk purchasing discounts, efficient machinery).
   • Diseconomies of Scale: Rising LRAC as plant size becomes overly gigantic (due to bureaucratic red tape, communication bottlenecks, management coordination loss).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The rational producer ALWAYS operates in STAGE II of production (where MPL is decreasing but still positive).
• Total Product is MAXIMIZED when Marginal Product equals zero (MP = 0).
• Marginal Cost intersects Average Variable Cost and Average Total Cost at their MINIMUM points!
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 96
    ),
    ShortNote(
        id = "econ-u5",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 5",
        title = "Market Structures: Perfect Competition, Monopoly & Oligopoly",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Market structures define the organizational environment in which firms compete. Economics evaluates industries across a continuum: from Perfect Competition (price-taking, efficiency) to Pure Monopoly (price-making, deadweight loss), along with intermediate structures: Monopolistic Competition and Oligopoly.

🔍 DETAILED BREAKDOWN
1. Perfect Competition:
   • Core Characteristics:
     - Infinite number of small buyers and sellers (no single actor influences price).
     - Standardized, identical, homogeneous products.
     - Free entry and exit of firms in the long run.
     - Perfect information among all market participants.
     - Perfect mobility of factors of production.
   • Demand Curve Facing a Competitive Firm:
     - The firm is a PRICE TAKER: Faces a perfectly horizontal, infinitely elastic demand curve at the market price: P = MR = AR = d.
   • Short-Run Profit Maximization:
     - Golden Profit Maximization Rule: Produce output where MR = MC (and MC is rising). Under perfect competition: P = MC.
   • Short-Run Operating Scenarios:
     - P > ATC: Supernormal / Economic Profit.
     - P = ATC: Normal Profit (Break-even point).
     - AVC <= P < ATC: Economic Loss, but CONTINUES operating in the short run (revenue covers all variable costs and contributes toward fixed costs).
     - Shutdown Point: P = min(AVC). If P < AVC, the firm SHUTS DOWN operations immediately to minimize losses strictly to TFC.
   • Short-Run Supply Curve of a Competitive Firm: The portion of its Marginal Cost (MC) curve that lies ABOVE the minimum point of AVC.
   • Long-Run Equilibrium: Free entry eliminates profits, and free exit eliminates losses:
     - Condition: P = MR = MC = min(ATC). Zero economic profit (earns normal profit).
     - Achieves BOTH Allocative Efficiency (P = MC) and Productive Efficiency (P = min ATC).

2. Pure Monopoly:
   • Core Characteristics: Single seller (firm is the entire industry), unique product with NO close substitutes, insurmountable barriers to entry (patents, natural monopolies, legal franchises, control of strategic raw inputs), firm is a PRICE MAKER.
   • Demand and Marginal Revenue:
     - Faces the downward-sloping market demand curve.
     - To sell additional units, the monopolist must lower the price on ALL units sold; therefore, Marginal Revenue is strictly LESS than Price (MR < P).
   • Profit Maximization: Produce quantity Qm where MR = MC; price Pm is determined by reading vertically up to the demand curve (Pm > MC).
   • Social Welfare Loss: Monopolist produces less output and charges higher prices than competitive markets, causing Allocative Inefficiency and Deadweight Welfare Loss.
   • Price Discrimination: Selling the identical good to different consumers at different prices for reasons unrelated to cost differences:
     - First-Degree (Perfect): Charges each consumer their maximum willingness to pay; captures all consumer surplus.
     - Second-Degree: Charges different prices based on quantity/volume purchased (block pricing, bulk discounts).
     - Third-Degree: Segregates market by differing price elasticities (e.g., student discounts, peak vs. off-peak transit fares).

3. Monopolistic Competition:
   • Characteristics: Large number of sellers, PRODUCT DIFFERENTIATION (physical attributes, branding, packaging, service quality), free entry and exit, heavy advertising/non-price competition.
   • Downward-sloping, highly elastic demand curve.
   • Short Run: Can earn economic profit or incur loss (MR = MC).
   • Long Run: Free entry shifts individual demand curve leftward until P = ATC (tangency point). Zero economic profit in long run.
   • Excess Capacity Theorem: Produces at an output level below the minimum efficient scale (fails productive efficiency).

4. Oligopoly:
   • Characteristics: Small number of large dominant firms (e.g., telecom, airlines, cement), high barriers to entry, MUTUAL INTERDEPENDENCE (every firm's decisions on price and output directly impact rivals and trigger retaliation).
   • Models of Oligopoly:
     - Non-Collusive Oligopoly:
       * Kinked Demand Curve Model (Paul Sweezy): Explains price rigidity. Assumes rivals match price cuts (inelastic) but ignore price hikes (elastic).
       * Game Theory & Prisoner's Dilemma: Models strategic decision-making, dominant strategies, and Nash Equilibrium.
     - Collusive Oligopoly:
       * Cartel (e.g., OPEC): Formal explicit agreement among oligopolists to fix prices, restrict output, and divide markets to act as a shared monopoly.
       * Price Leadership: Dominant firm sets price, which smaller firms follow.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Profit Maximization Rule for ALL firms: MR = MC.
• Shutdown Rule: A firm shuts down in the short run if Price falls below minimum Average Variable Cost (P < min AVC).
• Competitive firm: P = MR = MC. Monopoly / Imperfect competition: MR < P.
• In the long run, both Perfect Competition and Monopolistic Competition earn ZERO economic profit (normal profit).
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 97
    ),
    ShortNote(
        id = "econ-u6",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 6",
        title = "Fundamentals of Macroeconomics, GDP, Inflation & Unemployment",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Macroeconomics analyzes national aggregate output, general price stability, employment, and economic growth. Central to macroeconomic evaluation are National Income Accounting (GDP and GNP), business cycle fluctuations, inflation measurement, and unemployment dynamics.

🔍 DETAILED BREAKDOWN
1. National Income Accounting (GDP vs. GNP):
   • Gross Domestic Product (GDP): The total market value of all FINAL goods and services produced within the geographic boundaries of a country during a given period (usually one year).
   • What GDP Excludes: Intermediate goods (avoids double counting), transfer payments (pensions, student allowances), secondhand used goods, financial transactions (stocks/bonds), illegal/underground market activities, non-market household labor.
   • GDP vs. Gross National Product (GNP):
     - GNP = GDP + Net Factor Income from Abroad (NFIA).
     - NFIA = Factor income earned by domestic citizens abroad - Factor income earned by foreign citizens domestically.
     - GDP measures production within domestic territory; GNP measures production by domestic citizens regardless of location.
   • Three Approaches to Measure GDP:
     1. Expenditure Approach: GDP = C + I + G + (X - M)
        - C = Personal Consumption expenditure.
        - I = Gross Private Domestic Investment (I = Net Investment + Depreciation).
        - G = Government Purchases of goods and services (excludes transfer payments).
        - (X - M) = Net Exports (Exports X minus Imports M).
     2. Income Approach: GDP = Wages + Rent + Interest + Corporate Profits + Proprietor's Income + Indirect Business Taxes + Depreciation - Subsidies.
     3. Value-Added (Product) Approach: Sum of value added across all production stages (Value of Output - Value of Intermediate Inputs).
   • Other National Income Measures:
     - Net Domestic Product (NDP) = GDP - Depreciation (Capital Consumption Allowance).
     - National Income (NI) = NDP - Indirect Business Taxes + Subsidies.
     - Personal Income (PI) = NI - Undistributed Corporate Profits - Corporate Taxes - Social Security + Transfer Payments.
     - Disposable Personal Income (DPI) = PI - Personal Income Taxes = Consumption (C) + Savings (S).
   • Nominal GDP vs. Real GDP & The GDP Deflator:
     - Nominal GDP: Valued at current-year prices (reflects both output changes and price inflation).
     - Real GDP: Valued at constant base-year prices (measures true physical output growth).
     - GDP Deflator: (Nominal GDP / Real GDP) × 100.
     - Real GDP per Capita = Real GDP / Total Population.

2. The Business Cycle:
   • Periodic recurrent fluctuations in aggregate economic activity around the long-run growth trend.
   • Four Phases:
     1. Peak (Boom): Output at maximum capacity, high employment, upward pressure on price levels.
     2. Recession (Contraction): Two or more consecutive quarters of declining real GDP, rising unemployment.
     3. Trough: Lowest turning point of economic activity and output.
     4. Expansion (Recovery): Real GDP and employment rise back toward potential output.

3. Unemployment:
   • Labor Force: Non-institutionalized working-age individuals who are employed or actively seeking employment. Excludes full-time students, homemakers, retirees, and discouraged workers.
   • Unemployment Rate: (Number of Unemployed / Total Labor Force) × 100.
   • Four Major Types of Unemployment:
     1. Frictional: Temporary unemployment of workers voluntarily transitioning between jobs or new graduates seeking employment.
     2. Structural: Mismatch between workers' skills and demands of employers caused by technological innovation or geographic relocation.
     3. Cyclical: Caused by macroeconomic downturns and deficiency of aggregate demand during recessions.
     4. Seasonal: Caused by predictable seasonal shifts in agricultural, construction, or tourism labor demand.
   • Natural Rate of Unemployment (NRU / Full Employment): Frictional + Structural Unemployment (occurs when cyclical unemployment is ZERO).
   • Okun's Law: For every 1% real GDP falls below potential output, unemployment rises by roughly 0.5% (or 1% cyclical unemployment creates ~2% GDP gap).

4. Inflation:
   • Definition: A sustained, generalized increase in the overall price level across the economy over time.
   • Consumer Price Index (CPI): Measures cost of a fixed market basket of goods bought by a typical urban consumer:
     - CPI = (Cost of Basket in Current Year / Cost of Basket in Base Year) × 100.
     - Inflation Rate = [(CPIt - CPIt-1) / CPIt-1] × 100.
   • Types & Causes of Inflation:
     - Demand-Pull Inflation: Aggregate demand exceeds aggregate supply at full employment ("too much money chasing too few goods").
     - Cost-Push Inflation: Rising production costs (wages, raw materials, fuel import shocks) shift SRAS leftward, causing Stagflation (simultaneous high inflation and rising unemployment).
   • Redistributive Effects: Hurts fixed-income earners, savers, and creditors; benefits debtors/borrowers.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Intermediate goods and transfer payments are EXCLUDED from GDP to prevent double counting!
• GDP Deflator = (Nominal GDP / Real GDP) × 100.
• Full Employment (Natural Rate of Unemployment) does NOT mean 0% unemployment; it means CYCLICAL unemployment is zero (NRU = Frictional + Structural).
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 93
    ),
    ShortNote(
        id = "econ-u7",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 7",
        title = "Macroeconomic Policies, Financial Systems & International Trade",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Governments and central banks manage economic stability through fiscal and monetary policies. In an interconnected global economy, international trade governed by comparative advantage and foreign exchange mechanisms plays a vital role in national development.

🔍 DETAILED BREAKDOWN
1. Aggregate Demand - Aggregate Supply (AD-AS) Framework:
   • Aggregate Demand (AD): Total quantity of final domestic goods demanded at various price levels. Downward sloping due to:
     - Real Wealth Effect (Pigou Effect): Lower prices increase real value of wealth, boosting consumption.
     - Interest Rate Effect (Keynes Effect): Lower prices reduce money demand, lowering interest rates and increasing investment.
     - Exchange Rate Effect (Mundell-Fleming): Lower domestic interest rates cause capital outflow, depreciating currency and boosting net exports.
   • Aggregate Supply (AS):
     - Short-Run Aggregate Supply (SRAS): Upward sloping due to sticky wages and input price rigidities.
     - Long-Run Aggregate Supply (LRAS): Perfectly vertical at Potential / Full-Employment Output (Yp), determined by labor, capital, and technology.
   • Gaps: Recessionary Gap (Actual GDP < Yp) vs. Inflationary Gap (Actual GDP > Yp).

2. Fiscal Policy & Multipliers:
   • Definition: Government manipulation of taxation (T) and public expenditure (G) to influence aggregate demand and economic activity.
   • Types:
     - Expansionary Fiscal Policy: Increasing government spending (G) or cutting taxes (T) to fight recessions (shifts AD right).
     - Contractionary Fiscal Policy: Decreasing government spending (G) or raising taxes (T) to cool inflation (shifts AD left).
   • Keynesian Multipliers:
     - Marginal Propensity to Consume: MPC = ΔC / ΔY; Marginal Propensity to Save: MPS = ΔS / ΔY; MPC + MPS = 1.
     - Government Spending Multiplier: kg = 1 / (1 - MPC) = 1 / MPS.
     - Tax Multiplier: kt = -MPC / (1 - MPC) = -MPC / MPS (always negative and smaller in absolute value than kg).
     - Balanced Budget Multiplier: If ΔG = ΔT, the balanced budget multiplier equals exactly 1.
   • Automatic Stabilizers: Built-in tax/spending mechanisms that automatically cushion fluctuations without new legislation (e.g., progressive income tax, welfare payments).
   • The Crowding-Out Effect: Government deficit borrowing drives up real interest rates, reducing private domestic investment.

3. Money, Banking & Monetary Policy:
   • Functions of Money: Medium of exchange (eliminates barter double coincidence of wants), Unit of account (standard measure of value), Store of value (transfers purchasing power across time).
   • Money Supply Aggregates:
     - M1 (Narrow Money): Currency in circulation + Demand/checkable deposits.
     - M2 (Broad Money): M1 + Savings deposits + Short-term time deposits.
   • Fractional Reserve Banking & Money Creation:
     - Banks keep a required reserve fraction (rr) and lend out excess reserves.
     - Simple Money Multiplier: m = 1 / rr.
     - Maximum Potential Money Expansion: ΔM = Initial Excess Deposit × (1 / rr).
   • Central Bank (National Bank of Ethiopia - NBE) Monetary Policy Tools:
     1. Open Market Operations (OMO): Buying treasury bills injects reserves (expansionary); selling bills absorbs reserves (contractionary).
     2. Required Reserve Ratio (rr): Lowering rr increases lending (expansionary); raising rr restricts credit (contractionary).
     3. Discount Rate / Policy Lending Rate: Interest rate charged to commercial banks on emergency loans.
     4. Direct Credit Controls and Moral Suasion.

4. International Economics, Trade & Exchange Rates:
   • Classical Trade Theories:
     - Absolute Advantage (Adam Smith): A country should specialize in producing goods it can manufacture using fewer inputs than other nations.
     - Comparative Advantage (David Ricardo): A country should specialize in and export goods in which it has the LOWEST OPPORTUNITY COST. Mutual gains from trade occur whenever countries trade based on comparative advantage!
   • Balance of Payments (BOP): Complete accounting record of all economic transactions between domestic residents and the rest of the world over a year:
     - Current Account: Trade balance in merchandise goods, services, net primary factor income from abroad, and unilateral secondary transfers (diaspora remittances).
     - Capital and Financial Account: Foreign Direct Investment (FDI), portfolio equity investments, official external debt and borrowing.
     - Official Reserves Account: Central bank foreign exchange reserve adjustments.
   • Exchange Rate Systems:
     - Fixed (Pegged) Exchange Rate: Set and maintained by central bank intervention.
     - Floating (Flexible) Exchange Rate: Determined entirely by foreign exchange supply and demand.
     - Devaluation / Depreciation: Lowers foreign price of domestic exports, enhancing export competitiveness while making imports more expensive.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Comparative Advantage is based on OPPORTUNITY COST, not absolute productivity!
• Spending Multiplier (1 / MPS) is ALWAYS greater in magnitude than the Tax Multiplier (-MPC / MPS).
• Money Multiplier = 1 / rr. If the reserve requirement is 10% (0.10), the money multiplier is 10.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 95
    )
)
