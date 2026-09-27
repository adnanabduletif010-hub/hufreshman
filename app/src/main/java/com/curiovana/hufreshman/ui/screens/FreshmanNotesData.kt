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
        title = "Physics & Measurement, Dimensions & Vectors",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Physics is an experimental science rooted in precise measurement. All physical quantities are divided into fundamental (base) quantities and derived quantities. Measurement involves uncertainty, governed by rules of significant figures and dimensional consistency.

🔍 DETAILED BREAKDOWN
1. Standards & SI Base Units:
   • Length: Meter (m), defined by the speed of light in vacuum (c = 299,792,458 m/s).
   • Mass: Kilogram (kg), defined via Planck's constant (h = 6.62607015 × 10⁻³⁴ J·s).
   • Time: Second (s), based on Cesium-133 radiation oscillations.
   • Electric Current: Ampere (A); Temperature: Kelvin (K); Amount of Substance: Mole (mol); Luminous Intensity: Candela (cd).

2. Dimensional Analysis:
   • Dimensions describe the physical nature of a quantity: [M] mass, [L] length, [T] time, [I] electric current, [θ] temperature.
   • Common Dimensions: Velocity [v] = LT⁻¹; Acceleration [a] = LT⁻²; Force [F] = MLT⁻²; Work/Energy [W] = ML²T⁻²; Power [P] = ML²T⁻³; Pressure [P] = ML⁻¹T⁻²; Frequency [f] = T⁻¹.
   • Principle of Dimensional Homogeneity: Terms on both sides of any valid physical equation MUST have identical dimensions. Arguments of transcendental functions (sin, exp, ln) must be dimensionless.

3. Vectors & Coordinate Systems:
   • Scalar: Magnitude only with appropriate units (e.g., mass, time, temperature, work, electric potential).
   • Vector: Both magnitude and direction, obeying parallelogram law (e.g., displacement, velocity, acceleration, force, electric field).
   • Unit Vectors: Dimensionless vectors of unit length along axes: î (x-axis), ĵ (y-axis), k̂ (z-axis).
   • Vector Resolution: For vector A at angle θ with x-axis: A_x = A cos θ, A_y = A sin θ, |A| = √(A_x² + A_y²), tan θ = A_y / A_x.

4. Vector Multiplication:
   • Scalar (Dot) Product: A · B = |A||B| cos θ = A_x B_x + A_y B_y + A_z B_z. Commutative: A · B = B · A. If A ⊥ B, A · B = 0.
   • Vector (Cross) Product: A × B = |A||B| sin θ n̂. Anti-commutative: A × B = -(B × A). If A ∥ B, A × B = 0. Evaluated via determinant with î, ĵ, k̂.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• When checking dimensional correctness, remember that constants like ½, 2π, and trigonometric factors are dimensionless ([1]).
• Dot product produces a SCALAR (e.g., Work W = F · d), while Cross product produces a VECTOR perpendicular to both vectors (e.g., Torque τ = r × F).
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 78
    ),
    ShortNote(
        id = "phys-u2",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 2",
        title = "Kinematics in 1D & 2D (Linear & Projectile Motion)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Kinematics describes motion without considering the forces causing it. It tracks position, displacement, velocity, and acceleration in one dimension (rectilinear) and two dimensions (curvilinear, projectile motion, uniform circular motion).

🔍 DETAILED BREAKDOWN
1. Linear Kinematics:
   • Position & Displacement: Displacement Δx = x_f - x_i (vector); Distance is total path length traveled (scalar).
   • Velocity: Average velocity v_avg = Δx / Δt; Instantaneous velocity v(t) = dx/dt (slope of x-t graph).
   • Acceleration: Average acceleration a_avg = Δv / Δt; Instantaneous acceleration a(t) = dv/dt = d²x/dt² (slope of v-t graph). Area under v-t curve represents displacement.

2. Constant Acceleration Equations (1D Motion):
   1. v = v₀ + at
   2. x - x₀ = v₀t + ½at²
   3. v² = v₀² + 2a(x - x₀)
   4. x - x₀ = ½(v₀ + v)t
   • Free-Fall Motion: Motion solely under gravity with a = -g (-9.8 m/s² or -10 m/s²). At peak height, instantaneous vertical velocity v_y = 0.

3. Projectile Motion (2D Motion):
   • Assumes air resistance is negligible. Motion splits into two independent perpendicular components:
   • Horizontal Axis (No acceleration, a_x = 0):
     - Horizontal velocity is constant: v_x = v₀ cos θ
     - Horizontal displacement: x = (v₀ cos θ) t
   • Vertical Axis (Constant acceleration, a_y = -g):
     - Initial vertical velocity: v_y₀ = v₀ sin θ
     - Vertical velocity: v_y = v₀ sin θ - gt
     - Vertical displacement: y = (v₀ sin θ)t - ½gt²
   • Key Trajectory Formulas:
     - Time to Peak: t_peak = (v₀ sin θ) / g
     - Total Time of Flight: T = (2 v₀ sin θ) / g
     - Maximum Height: H_max = (v₀² sin²θ) / (2g)
     - Horizontal Range: R = (v₀² sin 2θ) / g (Maximum range occurs at launch angle θ = 45°).
     - Trajectory Equation: y = (tan θ)x - [g / (2v₀² cos²θ)] x² (parabolic profile).

4. Uniform Circular Motion:
   • Speed is constant, but velocity vector changes direction continuously.
   • Centripetal Acceleration: a_c = v² / r = ω² r, directed radially inward toward circle center.
   • Period & Frequency: T = 2πr / v = 2π / ω; f = 1 / T.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• In projectile problems, horizontal velocity NEVER changes: v_x(t) = v₀ cos θ throughout the entire flight.
• Range is identical for complementary angles of launch (e.g., θ = 30° and θ = 60° produce the same range R if initial speed v₀ is equal).
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 85
    ),
    ShortNote(
        id = "phys-u3",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 3",
        title = "Dynamics, Newton's Laws & Circular Dynamics",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Dynamics studies the relationship between forces and the resulting motion of objects. Isaac Newton formalized mechanics into three fundamental laws governing translational equilibrium, acceleration, and interactive contact forces.

🔍 DETAILED BREAKDOWN
1. Newton's Three Laws of Motion:
   • 1st Law (Law of Inertia): An object remains at rest or continues moving at constant velocity in a straight line unless acted upon by a net non-zero external force. Valid only in inertial reference frames.
   • 2nd Law: The acceleration of an object is directly proportional to net force and inversely proportional to its mass: ΣF = ma = dp/dt.
   • 3rd Law (Action-Reaction): For every action force, there exists an equal in magnitude and opposite in direction reaction force (F_AB = -F_BA). Crucial: Action and reaction forces act on DIFFERENT objects and never cancel each other out.

2. Types of Contact & Body Forces:
   • Gravitational Weight: W = mg, acting toward Earth's center.
   • Normal Force (N): Perpendicular force exerted by a contact surface. On flat ground N = mg; on plane inclined at angle θ, N = mg cos θ.
   • Tension (T): Pulling force transmitted through a taut ideal massless rope or string.

3. Friction Forces:
   • Static Friction (f_s): Opposes impending motion. It is self-adjusting up to a maximum limit: f_s ≤ f_s,max = μ_s N.
   • Kinetic Friction (f_k): Opposes ongoing sliding motion: f_k = μ_k N.
   • Coefficient relationship: Typically μ_s > μ_k. Friction depends on materials in contact and normal force, not on apparent contact surface area.

4. Free Body Diagrams (FBD) & Common Systems:
   • Inclined Plane: Gravity decomposes into mg sin θ (parallel down incline) and mg cos θ (perpendicular into surface). Acceleration down frictionless incline: a = g sin θ. With friction: a = g(sin θ - μ_k cos θ).
   • Atwood Machine: Two masses m₁, m₂ (m₂ > m₁) over a massless pulley: a = (m₂ - m₁)g / (m₁ + m₂); Tension T = (2 m₁ m₂ g) / (m₁ + m₂).

5. Circular Dynamics & Road Banking:
   • Centripetal Force: F_c = m a_c = (m v²) / r (provided by tension, gravity, or friction).
   • Unbanked Curved Road: Maximum safe speed without skidding depends entirely on static friction: v_max = √(μ_s g r).
   • Frictionless Banked Road: Optimal banking angle for design speed v: tan θ = v² / (r g).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The Normal force does NOT always equal mg! If a force pulls upward at an angle θ, N = mg - F sin θ; inside an accelerating elevator N = m(g ± a).
• Static friction reaches its peak value μ_s N only right before slippage occurs.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 92
    ),
    ShortNote(
        id = "phys-u4",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 4",
        title = "Work, Energy, Conservation Laws & Power",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Energy is the capacity to do work. Work and energy are scalar quantities measured in Joules (1 J = 1 N·m = 1 kg·m²/s²). The Work-Energy Theorem and the Principle of Conservation of Mechanical Energy provide powerful methods to solve dynamic problems without solving differential equations.

🔍 DETAILED BREAKDOWN
1. Work:
   • Work by Constant Force: W = F · d = |F||d| cos θ, where θ is the angle between force F and displacement d.
     - Positive Work (0° ≤ θ < 90°): Force aids motion (e.g., gravity on falling body).
     - Negative Work (90° < θ ≤ 180°): Force opposes motion (e.g., kinetic friction W_f = -f_k d).
     - Zero Work (θ = 90°): Force is perpendicular to displacement (e.g., normal force, centripetal force).
   • Work by Variable Force: W = ∫ F(x) dx (area under force vs position curve). For Hooke's Law spring (F = -kx): W_spring = -½ k (x_f² - x_i²).

2. Work-Kinetic Energy Theorem:
   • Kinetic Energy (Translational): K = ½ m v².
   • Theorem Statement: The net work done by all forces acting on a particle equals the change in kinetic energy: W_net = ΔK = K_f - K_i = ½ m v_f² - ½ m v_i².

3. Conservative vs Non-Conservative Forces:
   • Conservative Forces: Work done is independent of path taken, depending only on initial and final positions. Work around any closed loop is zero (∮ F · dr = 0). Examples: Gravity, electrostatic force, ideal spring force.
   • Potential Energy (U): Defined ONLY for conservative forces such that F = -dU/dx:
     - Gravitational Potential Energy: U_g = mgh (near Earth's surface).
     - Elastic Potential Energy: U_s = ½ k x² (where x is displacement from equilibrium).
   • Non-Conservative Forces: Work done depends on path taken (e.g., friction, air resistance, motor thrust). Energy is dissipated into internal/thermal energy.

4. Conservation of Mechanical Energy:
   • In an isolated system with only conservative forces: E_total = K + U = constant.
     => K_i + U_i = K_f + U_f.
   • In the presence of non-conservative forces: W_nc = ΔE_mech = (K_f + U_f) - (K_i + U_i).

5. Power:
   • Time rate of doing work or transferring energy.
   • Average Power: P_avg = ΔW / Δt.
   • Instantaneous Power: P = dW/dt = F · v.
   • SI Unit: Watt (1 W = 1 J/s). Common conversion: 1 Horsepower (hp) = 746 Watts; 1 kilowatt-hour (kWh) = 3.6 × 10⁶ J.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The centripetal force in uniform circular motion does ZERO work because force is always perpendicular to instantaneous velocity (cos 90° = 0).
• When using energy conservation, always set an explicit reference datum (y = 0) for gravitational potential energy.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 81
    ),
    ShortNote(
        id = "phys-u5",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 5",
        title = "Linear Momentum, Impulse & Collisions",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Linear momentum is a vector describing the quantity of motion of a moving body. The principle of conservation of linear momentum is one of the most fundamental conservation laws in physics, applying from subatomic particles to cosmic collisions.

🔍 DETAILED BREAKDOWN
1. Linear Momentum & Impulse:
   • Linear Momentum: p = mv (SI unit: kg·m/s). It is a vector in the same direction as velocity.
   • Newton's 2nd Law in terms of Momentum: ΣF_ext = dp/dt.
   • Impulse (J): The integral of force over the time interval of impact:
     J = ∫ F dt = F_avg Δt.
   • Impulse-Momentum Theorem: Impulse delivered to a particle equals its change in linear momentum: J = Δp = mv_f - mv_i.
   • Graphical representation: Impulse equals the area under the Force vs Time (F-t) curve.

2. Conservation of Linear Momentum:
   • For an isolated system with no net external forces (ΣF_ext = 0):
     p_total,initial = p_total,final => Σ m_i v_i = Σ m_i v_f.
   • Momentum is conserved independently along each Cartesian axis: Σ p_x,i = Σ p_x,f and Σ p_y,i = Σ p_y,f.

3. Classification of Collisions:
   • Perfectly Elastic Collision:
     - Linear momentum is conserved: m₁v₁i + m₂v₂i = m₁v₁f + m₂v₂f.
     - Total kinetic energy is conserved: ½m₁v₁i² + ½m₂v₂i² = ½m₁v₁f² + ½m₂v₂f².
     - Relative speed of approach equals relative speed of separation: (v₁i - v₂i) = -(v₁f - v₂f).
     - Head-on 1D elastic collision formulas (m₂ initially at rest, v₂i = 0):
       v₁f = [(m₁ - m₂) / (m₁ + m₂)] v₁i
       v₂f = [(2m₁) / (m₁ + m₂)] v₁i
   • Inelastic Collision:
     - Momentum is conserved, but kinetic energy is NOT conserved (some KE is converted into heat, acoustic energy, or structural deformation).
   • Completely (Perfectly) Inelastic Collision:
     - The colliding objects stick together and move with a single common final velocity V_f:
       V_f = (m₁v₁i + m₂v₂i) / (m₁ + m₂).
     - This collision type exhibits the MAXIMUM possible kinetic energy loss.

4. Center of Mass:
   • Position of Center of Mass: R_cm = (Σ m_i r_i) / (Σ m_i) = (1 / M) ∫ r dm.
   • Velocity of CM: V_cm = (Σ m_i v_i) / M = P_total / M. If ΣF_ext = 0, V_cm remains constant.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• In an isolated explosion or bullet firing, momentum is conserved: m_bullet v_bullet + M_gun v_recoil = 0 => recoil velocity v_recoil = -(m/M) v_bullet.
• Always remember: Kinetic energy is conserved ONLY in elastic collisions, but momentum is conserved in ALL collisions as long as the system is isolated.
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 89
    ),
    ShortNote(
        id = "phys-u6",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 6",
        title = "Rotational Motion, Moment of Inertia & Angular Momentum",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Rotational dynamics examines the motion of rigid bodies rotating about fixed or moving axes. Linear kinematic and dynamic quantities have exact rotational analogs: displacement (θ), velocity (ω), acceleration (α), mass/inertia (I), force/torque (τ), and momentum (L).

🔍 DETAILED BREAKDOWN
1. Rotational Kinematics & Relationships:
   • Angular Position & Displacement: θ (in radians); Arc length s = rθ.
   • Angular Velocity: ω = dθ/dt (rad/s); Tangential speed v_t = rω.
   • Angular Acceleration: α = dω/dt (rad/s²); Tangential acceleration a_t = rα; Radial/Centripetal acceleration a_r = v_t²/r = rω².
   • Constant Angular Acceleration Equations:
     1. ω = ω₀ + αt
     2. θ - θ₀ = ω₀t + ½αt²
     3. ω² = ω₀² + 2α(θ - θ₀)
     4. θ - θ₀ = ½(ω₀ + ω)t

2. Moment of Inertia & Rotational Kinetic Energy:
   • Moment of Inertia (Rotational Inertia): I = Σ m_i r_i² = ∫ r² dm (kg·m²). Represents resistance to angular acceleration.
   • Common Inertia Formulas:
     - Thin hoop/ring about central axis: I = MR²
     - Solid cylinder or disk: I = ½MR²
     - Solid uniform sphere: I = ⅖MR²; Thin spherical shell: I = ⅔MR²
     - Thin slender rod about center: I = (1/12)ML²; About one end: I = ⅓ML²
   • Parallel-Axis Theorem: I = I_cm + Md², where d is distance between parallel axis and center of mass axis.
   • Rotational Kinetic Energy: K_rot = ½Iω². Total KE of rolling body without slipping: K_total = ½M v_cm² + ½I_cm ω².

3. Torque & Newton's Second Law for Rotation:
   • Torque (Moment of Force): τ = r × F = r F sin φ = F d_lever (N·m). Counterclockwise is typically positive (+).
   • Rotational Second Law: Στ = Iα (valid about fixed axis or about center of mass).
   • Work & Power in Rotation: W = ∫ τ dθ; Instantaneous Power P = τω.

4. Angular Momentum & Conservation:
   • Angular Momentum of Particle: L = r × p = r m v sin φ.
   • Angular Momentum of Rigid Body: L = Iω (kg·m²/s).
   • Net External Torque: Στ_ext = dL/dt.
   • Law of Conservation of Angular Momentum: If Στ_ext = 0, total angular momentum is strictly conserved:
     L_initial = L_final  =>  I_i ω_i = I_f ω_f.
     (Classic example: Figure skater pulling arms inward decreases I, drastically increasing angular speed ω).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• For rolling without slipping: v_cm = Rω and a_cm = Rα. Friction is STATIC friction and does NO net work!
• When solving rolling down an incline problems, acceleration is a = (g sin θ) / (1 + I_cm/(MR²)). Objects with smaller I_cm/(MR²) (like solid sphere 0.4) reach the bottom faster than disks (0.5) or hoops (1.0).
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 112
    ),
    ShortNote(
        id = "phys-u7",
        subjectId = "c1",
        subject = "General Physics",
        unit = "Unit 7",
        title = "Oscillations, Mechanical Waves & Fluid Mechanics",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
This unit synthesizes periodic vibratory motion (Simple Harmonic Motion), mechanical wave propagation (sound and standing waves), and fluid statics/dynamics governed by continuity and Bernoulli's conservation of energy principles.

🔍 DETAILED BREAKDOWN
1. Simple Harmonic Motion (SHM):
   • Hooke's Law: Restoring force F = -kx.
   • Equation of Motion: d²x/dt² + (k/m)x = 0  =>  x(t) = A cos(ωt + φ).
   • Angular Frequency, Frequency & Period:
     ω = √(k/m); f = ω / (2π) = (1/2π)√(k/m); T = 1/f = 2π√(m/k).
   • Velocity & Acceleration:
     v(t) = -ωA sin(ωt + φ)  =>  v_max = ωA (at equilibrium position x = 0).
     a(t) = -ω²A cos(ωt + φ) = -ω²x  =>  a_max = ω²A (at maximum amplitude x = ±A).
   • Conservation of Energy in SHM: E = ½mv² + ½kx² = ½kA² = ½m(v_max)² (constant).
   • Simple Pendulum (for small angles θ < 15°): T = 2π√(L/g); Physical Pendulum: T = 2π√(I / (mgd)).

2. Mechanical Waves & Sound:
   • Wave Speed Equation: v = λf = λ / T.
   • Wave on a Stretched String: v = √(T_tension / μ), where μ = m/L is linear mass density.
   • Standing Waves & Resonance (String fixed at both ends):
     Normal modes λ_n = (2L) / n; Resonant frequencies f_n = n (v / 2L) = n f₁ (n = 1, 2, 3...).
   • Doppler Effect (Sound): Observed frequency f' = f [(v ± v_observer) / (v ∓ v_source)]. Upper signs correspond to approaching motion.

3. Fluid Statics:
   • Density: ρ = m/V; Pressure: P = F / A (1 Pa = 1 N/m²; 1 atm = 1.013 × 10⁵ Pa = 760 mmHg).
   • Hydrostatic Pressure with Depth: P = P₀ + ρgh (pressure depends ONLY on vertical depth and fluid density, not vessel shape).
   • Pascal's Principle: Pressure applied to an enclosed fluid is transmitted undiminished: F₁/A₁ = F₂/A₂.
   • Archimedes' Principle & Buoyancy: Buoyant force equals weight of displaced fluid:
     F_B = ρ_fluid · V_displaced · g. If floating: F_B = Weight_object  =>  (V_submerged / V_total) = ρ_object / ρ_fluid.

4. Fluid Dynamics (Ideal Fluid: Incompressible, non-viscous, laminar, irrotational):
   • Equation of Continuity (Conservation of Mass): A₁ v₁ = A₂ v₂ = Volume Flow Rate Q (constant).
   • Bernoulli's Equation (Conservation of Energy along streamline):
     P + ½ρv² + ρgy = constant  =>  P₁ + ½ρv₁² + ρgy₁ = P₂ + ½ρv₂² + ρgy₂.
   • Torricelli's Law (Efflux speed from tank orifice): v = √(2gh).
   • Venturi Effect: As pipe cross-section narrows, velocity v increases and fluid pressure P decreases.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• In SHM, total mechanical energy is proportional to the SQUARE of the amplitude (E ∝ A²). Doubling amplitude quadruples the energy!
• In Bernoulli's principle, high fluid speed means LOW static pressure (explains aerodynamic lift, atomizers, and chimney draft).
        """.trimIndent(),
        colorHex = 0xFF0052FE,
        initialLikes = 108
    ),

    // =========================================================================
    // 2. APPLIED MATHEMATICS I (c2, Units 1 - 7)
    // =========================================================================
    ShortNote(
        id = "math-u1",
        subjectId = "c2",
        subject = "Applied Mathematics I",
        unit = "Unit 1",
        title = "Vectors & Analytic Geometry of Space (Lines, Planes, Surfaces)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
This unit extends two-dimensional calculus to three-dimensional Euclidean space (R³), providing foundational geometric language for physics and engineering through vectors, dot and cross products, lines, and planes.

🔍 DETAILED BREAKDOWN
1. 3D Coordinates & Vector Algebra:
   • Distance between P₁(x₁,y₁,z₁) and P₂(x₂,y₂,z₂): d = √[(x₂-x₁)² + (y₂-y₁)² + (z₂-z₁)²].
   • Equation of a Sphere centered at (h, k, l) with radius R: (x - h)² + (y - k)² + (z - l)² = R².
   • Vector in component form: a = <a₁, a₂, a₃> = a₁î + a₂ĵ + a₃k̂; Magnitude: |a| = √(a₁² + a₂² + a₃²).
   • Unit vector in direction of a: u = a / |a|.

2. The Dot Product (Scalar Product):
   • Definition: a · b = a₁b₁ + a₂b₂ + a₃b₃ = |a||b| cos θ.
   • Orthogonality: Two non-zero vectors a and b are perpendicular (orthogonal) iff a · b = 0.
   • Vector Projection of b onto a:
     proj_a(b) = [(a · b) / |a|²] a.
   • Scalar Component of b along a: comp_a(b) = (a · b) / |a|.

3. The Cross Product (Vector Product):
   • Definition: a × b = det | î  ĵ  k̂ ; a₁ a₂ a₃ ; b₁ b₂ b₃ | = <a₂b₃ - a₃b₂, a₃b₁ - a₁b₃, a₁b₂ - a₂b₁>.
   • Properties: a × b = -(b × a); a × a = 0.
   • Geometric significance: |a × b| = |a||b| sin θ represents the AREA of the parallelogram spanned by a and b.
   • Area of triangle with vertices P, Q, R: Area = ½ |PQ × PR|.
   • Parallel vectors: a ∥ b iff a × b = 0.

4. Scalar Triple Product & Coplanarity:
   • Triple Product: a · (b × c) = det | a₁ a₂ a₃ ; b₁ b₂ b₃ ; c₁ c₂ c₃ |.
   • Geometric meaning: Absolute value |a · (b × c)| equals the VOLUME of the parallelepiped formed by vectors a, b, and c.
   • Three vectors are coplanar if and only if a · (b × c) = 0.

5. Lines and Planes in 3D Space:
   • Line through point r₀ = (x₀, y₀, z₀) parallel to direction vector v = <a, b, c>:
     - Vector equation: r(t) = r₀ + t v
     - Parametric equations: x = x₀ + at, y = y₀ + bt, z = z₀ + ct
     - Symmetric equations: (x - x₀)/a = (y - y₀)/b = (z - z₀)/c.
   • Plane through P₀(x₀, y₀, z₀) with normal vector n = <A, B, C>:
     - Standard scalar equation: A(x - x₀) + B(y - y₀) + C(z - z₀) = 0
     - General linear equation: Ax + By + Cz + D = 0.
   • Distance from point P₁(x₁, y₁, z₁) to plane Ax + By + Cz + D = 0:
     D = |A x₁ + B y₁ + C z₁ + D| / √(A² + B² + C²).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• To find a normal vector n perpendicular to a plane containing three non-collinear points P, Q, R, calculate the cross product: n = PQ × PR.
• Two planes are perpendicular if their normal vectors have dot product zero: n₁ · n₂ = 0.
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 95
    ),
    ShortNote(
        id = "math-u2",
        subjectId = "c2",
        subject = "Applied Mathematics I",
        unit = "Unit 2",
        title = "Limits, Continuity & Foundational Theorems",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Limits form the foundational basis of differential and integral calculus, formalizing the behavior of functions near specific points. Continuity guarantees smooth transitions without breaks, holes, or asymptotes.

🔍 DETAILED BREAKDOWN
1. Intuitive & Formal Definitions of Limit:
   • Intuitive: lim_{x→a} f(x) = L means f(x) gets arbitrarily close to L as x approaches a from either side.
   • Precise (ε - δ) Definition: For every real ε > 0, there exists a real δ > 0 such that:
     0 < |x - a| < δ  =>  |f(x) - L| < ε.
   • One-Sided Limits: Left-hand limit lim_{x→a⁻} f(x) and right-hand limit lim_{x→a⁺} f(x).
   • Limit Existence Theorem: lim_{x→a} f(x) = L exists if and only if:
     lim_{x→a⁻} f(x) = lim_{x→a⁺} f(x) = L.

2. Essential Limit Laws & Squeeze Theorem:
   • Limit Laws: Sum, Difference, Constant Multiple, Product, Quotient (if denominator limit ≠ 0), and Power rules.
   • Squeeze (Sandwich) Theorem: If g(x) ≤ f(x) ≤ h(x) on an open interval containing a (except possibly at a) and lim_{x→a} g(x) = lim_{x→a} h(x) = L, then:
     lim_{x→a} f(x) = L.
   • Classic Trigonometric Limits:
     - lim_{x→0} (sin x / x) = 1 (where x is in radians)
     - lim_{x→0} [(1 - cos x) / x] = 0
     - lim_{x→0} [(1 - cos x) / x²] = ½.

3. Limits Involving Infinity:
   • Infinite Limits: lim_{x→a} f(x) = ±∞ defines a Vertical Asymptote at line x = a.
   • Limits at Infinity: lim_{x→±∞} f(x) = L defines a Horizontal Asymptote at line y = L.
   • For rational function P(x)/Q(x): If deg(P) = deg(Q), limit at infinity is the ratio of leading coefficients; if deg(P) < deg(Q), limit is 0; if deg(P) > deg(Q), limit is ±∞.

4. Continuity:
   • Definition: A function f is continuous at x = a if and only if all three conditions are satisfied:
     1. f(a) is defined (a is in the domain of f).
     2. lim_{x→a} f(x) exists.
     3. lim_{x→a} f(x) = f(a).
   • Classification of Discontinuities:
     - Removable Discontinuity: lim_{x→a} f(x) exists, but f(a) is either undefined or ≠ limit.
     - Jump Discontinuity: One-sided limits exist but are unequal (lim_{x→a⁻} f(x) ≠ lim_{x→a⁺} f(x)).
     - Infinite Discontinuity: One or both one-sided limits are ±∞.

5. Major Continuity Theorems:
   • Intermediate Value Theorem (IVT): If f is continuous on closed interval [a, b] and N is any number strictly between f(a) and f(b), then there exists at least one c ∈ (a, b) such that f(c) = N.
     - Root Finding Application: If f(a) and f(b) have opposite signs (f(a)·f(b) < 0), there exists c ∈ (a, b) where f(c) = 0.
   • Extreme Value Theorem (EVT): If f is continuous on a closed interval [a, b], then f attains both an absolute maximum and an absolute minimum on [a, b].

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The expression 0/0 is indeterminate, NOT undefined. Always factor, rationalize radicals using conjugates, or apply trig identities to cancel vanishing terms.
• To prove a polynomial has a root in [a, b], state that the polynomial is continuous everywhere, verify f(a) and f(b) have opposite signs, and cite the Intermediate Value Theorem.
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 88
    ),
    ShortNote(
        id = "math-u3",
        subjectId = "c2",
        subject = "Applied Mathematics I",
        unit = "Unit 3",
        title = "Derivatives, Applications & Optimization",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
The derivative measures the instantaneous rate of change of a function and represents the slope of the tangent line. Applications include curve sketching, finding extreme values, motion analysis, and optimization.

🔍 DETAILED BREAKDOWN
1. Definition & Fundamental Rules:
   • Definition: f'(x) = lim_{h→0} [f(x+h) - f(x)] / h = lim_{z→x} [f(z) - f(x)] / (z - x).
   • Differentiability implies Continuity, but the converse is FALSE (e.g., f(x) = |x| is continuous at 0, but not differentiable because left/right derivatives differ).
   • Essential Derivative Rules:
     - Power Rule: d/dx (xⁿ) = n xⁿ⁻¹
     - Product Rule: (u v)' = u'v + u v'
     - Quotient Rule: (u / v)' = (u'v - u v') / v²
     - Chain Rule: d/dx [f(g(x))] = f'(g(x)) · g'(x).
     - Exponential & Log: d/dx (eˣ) = eˣ; d/dx (aˣ) = aˣ ln a; d/dx (ln x) = 1/x.
     - Trigonometric: d/dx (sin x) = cos x; d/dx (cos x) = -sin x; d/dx (tan x) = sec² x; d/dx (sec x) = sec x tan x.
     - Inverse Trig: d/dx (arcsin x) = 1 / √(1 - x²); d/dx (arctan x) = 1 / (1 + x²).

2. Implicit Differentiation & Higher Derivatives:
   • Applied when y is not expressed explicitly in terms of x. Differentiate both sides with respect to x treating y as a function y(x) and applying chain rule (d/dx [y²] = 2y dy/dx), then solve algebraically for dy/dx.

3. Mean Value Theorem (MVT):
   • If f is continuous on [a, b] and differentiable on (a, b), there exists at least one c ∈ (a, b) such that:
     f'(c) = [f(b) - f(a)] / (b - a).
   • Rolle's Theorem: If additionally f(a) = f(b), then there exists c ∈ (a, b) such that f'(c) = 0.

4. Curve Sketching & Extrema:
   • Critical Point: Any point c in domain of f where f'(c) = 0 or f'(c) does not exist.
   • First Derivative Test:
     - f'(x) changes from + to - at c => local maximum at c.
     - f'(x) changes from - to + at c => local minimum at c.
   • Concavity & Second Derivative Test:
     - f''(x) > 0 on an interval => curve is concave upward (CU).
     - f''(x) < 0 on an interval => curve is concave downward (CD).
     - Inflection Point: Point where curve changes concavity and has a tangent line.
     - If f'(c) = 0 and f''(c) > 0 => local minimum; if f''(c) < 0 => local maximum; if f''(c) = 0 => test is inconclusive.

5. Indeterminate Forms & L'Hôpital's Rule:
   • For limits of type 0/0 or ±∞/±∞:
     lim_{x→a} [f(x) / g(x)] = lim_{x→a} [f'(x) / g'(x)].
   • For product 0 · ∞, rewrite as f / (1/g). For exponential forms 0⁰, 1^∞, ∞⁰, use y = f(x)^g(x) => ln y = g(x) ln(f(x)).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• When applying L'Hôpital's rule, differentiate the numerator and denominator SEPARATELY (f'/g'). Do NOT use the quotient rule!
• To find absolute extrema on a closed interval [a, b], evaluate f(x) at all critical points in (a, b) AND at both endpoints a and b, then compare values directly.
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 104
    ),
    ShortNote(
        id = "math-u4",
        subjectId = "c2",
        subject = "Applied Mathematics I",
        unit = "Unit 4",
        title = "Integration Techniques (Substitution, Parts, Partial Fractions)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Integration is the reverse process of differentiation and the mechanism for computing accumulated quantities and areas. Mastering diverse analytical integration techniques is critical for solving differential and physical equations.

🔍 DETAILED BREAKDOWN
1. Antiderivatives & Fundamental Theorem of Calculus (FTC):
   • Standard Indefinite Integrals:
     - ∫ xⁿ dx = (xⁿ⁺¹) / (n + 1) + C (for n ≠ -1)
     - ∫ (1 / x) dx = ln|x| + C
     - ∫ eᵃˣ dx = (1/a) eᵃˣ + C
     - ∫ sin(kx) dx = -(1/k) cos(kx) + C; ∫ cos(kx) dx = (1/k) sin(kx) + C
     - ∫ sec² x dx = tan x + C; ∫ sec x tan x dx = sec x + C
     - ∫ dx / (a² + x²) = (1/a) arctan(x/a) + C
     - ∫ dx / √(a² - x²) = arcsin(x/a) + C.
   • Fundamental Theorem of Calculus:
     - FTC Part 1: d/dx [∫_a^x f(t) dt] = f(x). With Leibniz rule:
       d/dx [∫_u(x)^v(x) f(t) dt] = f(v(x)) v'(x) - f(u(x)) u'(x).
     - FTC Part 2 (Evaluation Theorem): ∫_a^b f(x) dx = F(b) - F(a), where F'(x) = f(x).

2. Integration by Substitution (u-substitution):
   • Formula: ∫ f(g(x)) g'(x) dx = ∫ f(u) du, with u = g(x) and du = g'(x) dx.
   • For definite integrals, adjust limits of integration:
     ∫_a^b f(g(x)) g'(x) dx = ∫_{g(a)}^{g(b)} f(u) du.

3. Integration by Parts:
   • Formula: ∫ u dv = u v - ∫ v du.
   • Selection priority for choosing u (LIATE Rule):
     - L: Logarithmic functions (ln x, log₂ x)
     - I: Inverse trigonometric functions (arcsin x, arctan x)
     - A: Algebraic / polynomial functions (x², 3x)
     - T: Trigonometric functions (sin x, cos x)
     - E: Exponential functions (eˣ, 2ˣ).
   • Tabular Integration: Fast method when polynomial u(x) is differentiated repeatedly until it reaches 0 while dv is repeatedly integrated.

4. Trigonometric Substitutions:
   • For expressions with:
     - √(a² - x²) => substitute x = a sin θ, dx = a cos θ dθ; identity: a² - x² = a² cos² θ.
     - √(a² + x²) => substitute x = a tan θ, dx = a sec² θ dθ; identity: a² + x² = a² sec² θ.
     - √(x² - a²) => substitute x = a sec θ, dx = a sec θ tan θ dθ; identity: x² - a² = a² tan² θ.

5. Integration of Rational Functions by Partial Fractions:
   • For rational function P(x)/Q(x): If deg(P) ≥ deg(Q), perform polynomial long division first.
   • Factor denominator Q(x) into linear and irreducible quadratic factors:
     - Distinct linear factor (ax + b) => A / (ax + b)
     - Repeated linear factor (ax + b)ᵏ => A₁/(ax+b) + A₂/(ax+b)² + ... + A_k/(ax+b)ᵏ
     - Irreducible quadratic (ax² + bx + c) => (Ax + B) / (ax² + bx + c).
   • Clear denominators and solve for unknown constants A, B, C via substitution or coefficient matching.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Don't forget the integration constant (+ C) for all indefinite integrals!
• In FTC Part 1 problems like d/dx ∫_0^{x³} sin(t²) dt, remember to multiply by the derivative of the upper limit: sin((x³)²) · (3x²) = 3x² sin(x⁶).
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 97
    ),
    ShortNote(
        id = "math-u5",
        subjectId = "c2",
        subject = "Applied Mathematics I",
        unit = "Unit 5",
        title = "Applications of Integrals (Areas, Volumes & Arc Length)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Definite integrals sum infinitesimal slices to compute geometric and physical quantities including planar areas, volumes of revolution, arc lengths, surface areas, and average values.

🔍 DETAILED BREAKDOWN
1. Area Between Curves:
   • Area between y = f(x) and y = g(x) from x = a to x = b, with f(x) ≥ g(x):
     A = ∫_a^b [f(x) - g(x)] dx = ∫_a^b [y_top - y_bottom] dx.
   • If integrating with respect to y (curves x = f(y) and x = g(y), with f(y) ≥ g(y)):
     A = ∫_c^d [f(y) - g(y)] dy = ∫_c^d [x_right - x_left] dy.
   • If curves cross each other, split the integral at intersection points: A = ∫ |f(x) - g(x)| dx.

2. Volumes of Solids of Revolution:
   • Disk Method (Cross-section perpendicular to axis of revolution, solid core):
     - Revolution about x-axis: V = π ∫_a^b [R(x)]² dx.
     - Revolution about y-axis: V = π ∫_c^d [R(y)]² dy.
   • Washer Method (Solid with a hollow hole/inner core):
     - Revolution about x-axis: V = π ∫_a^b ([R(x)]² - [r(x)]²) dx, where R(x) is outer radius and r(x) is inner radius from rotation axis.
   • Cylindrical Shells Method (Slicing parallel to axis of revolution):
     - Revolution about vertical axis (e.g. y-axis):
       V = 2π ∫_a^b (radius)(height) dx = 2π ∫_a^b x [f(x) - g(x)] dx.
     - Revolution about horizontal axis (e.g. x-axis):
       V = 2π ∫_c^d y [f(y) - g(y)] dy.

3. Arc Length of a Curve:
   • Arc length L of smooth curve y = f(x) from x = a to x = b:
     L = ∫_a^b √(1 + [f'(x)]²) dx = ∫_a^b √(1 + (dy/dx)²) dx.
   • For curve x = g(y) from y = c to y = d:
     L = ∫_c^d √(1 + [g'(y)]²) dy = ∫_c^d √(1 + (dx/dy)²) dy.

4. Area of Surface of Revolution:
   • Rotating smooth curve y = f(x) about the x-axis:
     S = 2π ∫_a^b y ds = 2π ∫_a^b f(x) √(1 + [f'(x)]²) dx.
   • Rotating curve about the y-axis:
     S = 2π ∫_a^b x ds = 2π ∫_a^b x √(1 + [f'(x)]²) dx.

5. Average Value of a Function:
   • Formula: f_avg = [1 / (b - a)] ∫_a^b f(x) dx.
   • Mean Value Theorem for Integrals: If f is continuous on [a, b], there exists at least one number c ∈ [a, b] such that:
     f(c) = f_avg => ∫_a^b f(x) dx = f(c)(b - a).
     (Geometrically, area under curve equals area of rectangle with width (b-a) and height f(c)).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Washer method vs Shell method choice:
  - If revolving around x-axis: Washer uses dx, Shell uses dy.
  - If revolving around y-axis: Washer uses dy, Shell uses dx. Choose the one whose integrand is simpler to integrate!
• In Washer method, compute (R² - r²), NOT (R - r)². Squaring must occur BEFORE subtraction!
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 91
    ),
    ShortNote(
        id = "math-u6",
        subjectId = "c2",
        subject = "Applied Mathematics I",
        unit = "Unit 6",
        title = "Multivariable Calculus, Partial Derivatives & Double Integrals",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Multivariable calculus extends single-variable concepts to functions of several independent variables z = f(x, y). It analyzes rates of change in multidimensional space through partial derivatives and evaluates volumes, masses, and planar areas using double integrals.

🔍 DETAILED BREAKDOWN
1. Functions of Several Variables & Limits:
   • Domain & Range: Set of ordered pairs (x, y) for which the function is defined. Level Curves (contour lines) f(x, y) = c represent horizontal slices of 3D surfaces.
   • Multivariable Limits: lim_{(x,y)→(x₀,y₀)} f(x, y) = L exists ONLY if f(x, y) approaches L along EVERY possible path approaching (x₀, y₀).
   • Two-Path Test for Non-Existence: If f(x, y) approaches different values along two distinct curves (e.g., y = mx vs y = kx²), the limit does NOT exist.

2. Partial Derivatives, Gradient Vector & Directional Derivatives:
   • Partial Derivatives:
     - ∂f/∂x = f_x: Differentiate with respect to x treating y as a constant.
     - ∂f/∂y = f_y: Differentiate with respect to y treating x as a constant.
     - Clairaut's Theorem (Schwarz's Theorem): If mixed partials f_xy and f_yx are continuous on an open disk, then f_xy = f_yx.
   • Gradient Vector:
     - ∇f(x, y) = ⟨f_x(x, y), f_y(x, y)⟩ = (∂f/∂x) î + (∂f/∂y) ĵ.
     - Geometric Properties: ∇f points in the direction of MAXIMUM rate of increase of f; |∇f| is the maximum rate of increase; ∇f is ORTHOGONAL (perpendicular) to the level curve f(x, y) = k at (x₀, y₀).
   • Directional Derivative:
     - Rate of change of f in the direction of unit vector u = ⟨u₁, u₂⟩:
       D_u f(x, y) = ∇f(x, y) · u = f_x cos θ + f_y sin θ.

3. Double Integrals over General Regions:
   • Fubini's Theorem: For continuous f on rectangle R = [a, b] × [c, d]:
     ∬_R f(x, y) dA = ∫_a^b ∫_c^d f(x, y) dy dx = ∫_c^d ∫_a^b f(x, y) dx dy.
   • Type I Region: Bounded by continuous curves y = g₁(x) and y = g₂(x) over a ≤ x ≤ b:
     ∬_D f(x, y) dA = ∫_a^b [∫_{g₁(x)}^{g₂(x)} f(x, y) dy] dx.
   • Type II Region: Bounded by continuous curves x = h₁(y) and x = h₂(y) over c ≤ y ≤ d:
     ∬_D f(x, y) dA = ∫_c^d [∫_{h₁(y)}^{h₂(y)} f(x, y) dx] dy.
   • Reversing Order of Integration: Essential technique when the inner integrand has no elementary antiderivative (e.g., ∫₀¹ ∫_y¹ e^{x²} dx dy).

4. Polar Coordinates in Double Integrals & Geometric Applications:
   • Coordinate Transformation: x = r cos θ, y = r sin θ, x² + y² = r².
   • Area Element (Jacobian): dA = dx dy = r dr dθ (NEVER forget the extra factor r!).
     ∬_R f(x, y) dA = ∫_α^β ∫_{r₁}^{r₂} f(r cos θ, r sin θ) r dr dθ.
   • Key Applications:
     - Area of Region D: Area = ∬_D 1 dA.
     - Volume under surface z = f(x, y) ≥ 0: V = ∬_D f(x, y) dA.
     - Mass of planar lamina with density ρ(x, y): M = ∬_D ρ(x, y) dA.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The most common student error in polar double integration is forgetting the Jacobian r in r dr dθ.
• Directional derivative formula D_u f = ∇f · u requires u to be a UNIT vector (|u| = 1). If given a generic vector v, always normalize: u = v / |v|.
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 88
    ),
    ShortNote(
        id = "math-u7",
        subjectId = "c2",
        subject = "Applied Mathematics I",
        unit = "Unit 7",
        title = "Infinite Sequences, Series Convergence Tests & Power / Taylor Series",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Infinite series represent the summation of infinitely many terms. Understanding convergence criteria is fundamental to approximating transcendental functions through polynomial expansions (Taylor and Maclaurin series).

🔍 DETAILED BREAKDOWN
1. Sequences & Divergence Test:
   • Sequence Convergence: A sequence {a_n} converges to limit L if lim_{n→∞} a_n = L.
   • Monotonic Sequence Theorem: Every bounded, monotonic sequence is convergent.
   • Test for Divergence (nth-Term Test): If lim_{n→∞} a_n ≠ 0 or does not exist, the series ∑ a_n DIVERGES.
     - WARNING: If lim_{n→∞} a_n = 0, the test is INCONCLUSIVE (e.g., harmonic series ∑ 1/n diverges even though 1/n → 0).

2. Benchmark Series & Fundamental Tests:
   • Geometric Series: ∑_{n=0}^∞ ar^n = a + ar + ar² + ...
     - Converges to S = a / (1 - r) if and only if |r| < 1. Diverges if |r| ≥ 1.
   • p-Series Test: ∑_{n=1}^∞ 1 / n^p.
     - Converges if p > 1.
     - Diverges if p ≤ 1 (p = 1 is the divergent Harmonic Series).
   • Integral Test: If f(x) is continuous, positive, and decreasing on [1, ∞) with f(n) = a_n:
     ∑ a_n and ∫₁^∞ f(x) dx either BOTH converge or BOTH diverge.
   • Direct Comparison Test (DCT): If 0 ≤ a_n ≤ b_n for all n:
     - If ∑ b_n converges => ∑ a_n converges.
     - If ∑ a_n diverges => ∑ b_n diverges.
   • Limit Comparison Test (LCT): If a_n > 0, b_n > 0 and lim_{n→∞} (a_n / b_n) = c (where 0 < c < ∞):
     Both series either BOTH converge or BOTH diverge.

3. Advanced Tests & Alternating Series:
   • Ratio Test: L = lim_{n→∞} |a_{n+1} / a_n|.
     - If L < 1: Absolutely convergent.
     - If L > 1: Divergent.
     - If L = 1: Inconclusive (must use another test; standard for p-series and rational functions). Ideal for factorials (n!) and exponentials (c^n).
   • Root Test: L = lim_{n→∞} (|a_n|)^{1/n}. Converges if L < 1, diverges if L > 1.
   • Alternating Series Test (Leibniz's Test): Series ∑ (-1)^n b_n (b_n > 0) converges if:
     1. b_{n+1} ≤ b_n for all n (terms are non-increasing), AND
     2. lim_{n→∞} b_n = 0.
   • Absolute vs Conditional Convergence:
     - Absolutely Convergent: If ∑ |a_n| converges (implies ∑ a_n also converges).
     - Conditionally Convergent: If ∑ a_n converges, but ∑ |a_n| diverges (e.g., alternating harmonic series ∑ (-1)^{n+1}/n).

4. Power Series, Radius of Convergence & Taylor Series:
   • Power Series: ∑_{n=0}^∞ c_n (x - a)^n centered at x = a.
     - Radius of Convergence R: Found via Ratio Test lim |(c_{n+1}/c_n)(x - a)| < 1.
     - Interval of Convergence: (a - R, a + R), with endpoints x = a ± R tested individually.
   • Taylor & Maclaurin Series:
     - Taylor series of f(x) centered at a: f(x) = ∑_{n=0}^∞ [f^(n)(a) / n!] (x - a)^n.
     - Maclaurin series: Taylor series centered at a = 0.
   • Essential Maclaurin Series to Memorize:
     - e^x = ∑_{n=0}^∞ x^n / n! = 1 + x + x²/2! + x³/3! + ... (R = ∞)
     - sin x = ∑_{n=0}^∞ (-1)^n x^{2n+1} / (2n+1)! = x - x³/3! + x⁵/5! - ... (R = ∞)
     - cos x = ∑_{n=0}^∞ (-1)^n x^{2n} / (2n)! = 1 - x²/2! + x⁴/4! - ... (R = ∞)
     - 1 / (1 - x) = ∑_{n=0}^∞ x^n = 1 + x + x² + x³ + ... (for |x| < 1).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Remember: nth-term test can ONLY prove divergence, NEVER convergence!
• In finding interval of convergence for power series, ALWAYS test the boundary endpoints separately using p-series or alternating series test.
        """.trimIndent(),
        colorHex = 0xFF4F46E5,
        initialLikes = 94
    ),

    // =========================================================================
    // 3. GENERAL PSYCHOLOGY (c3, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "psyc-u1",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 1",
        title = "Essence of Psychology, Schools & Research Foundations",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Psychology is the scientific study of human behavior (overt, directly observable actions) and mental processes (covert, internal experiences like thoughts, emotions, and memories). It seeks empirical evidence to understand mind and action.

🔍 DETAILED BREAKDOWN
1. Four Primary Goals of Psychology:
   • Description: Accurately observing and recording behavior (What is happening?).
   • Explanation: Determining causes, conditions, and reasons behind behaviors (Why does it happen?). Formulates hypotheses and theories.
   • Prediction: Foreseeing future behaviors under specific environmental conditions (When will it occur?).
   • Control / Modification: Applying psychological principles to enhance human functioning and alleviate distress (How can it be changed?).

2. Historical Evolution & Major Schools of Thought:
   • Structuralism (Wilhelm Wundt, 1879 Leipzig - Father of experimental psychology; Edward Titchener): Analyzed the basic components of conscious experience using systematic introspection.
   • Functionalism (William James): Influenced by Darwin; focused on the adaptive purpose and function of consciousness in survival rather than static elements.
   • Gestalt Psychology (Max Wertheimer, Kurt Koffka, Wolfgang Köhler): Argued that "The whole is greater than the sum of its parts." Studied visual perception and problem solving.
   • Behaviorism (John B. Watson, B.F. Skinner): Rejected introspection and the study of unobservable consciousness; insisted psychology study only observable, measurable stimulus-response behaviors.
   • Psychoanalysis (Sigmund Freud): Emphasized the power of the unconscious mind, unresolved early childhood conflicts, and aggressive/sexual drives.
   • Humanistic Psychology (Carl Rogers, Abraham Maslow): "Third Force"; emphasized conscious free will, innate human goodness, personal growth, and self-actualization.
   • Cognitive Perspective: Focuses on mental processes like memory, thinking, language, perception, and problem-solving.
   • Biological/Neuroscience Perspective: Explores physiological, neural, hormonal, and genetic mechanisms underlying behavior.

3. Psychological Research Methods:
   • Descriptive Research: Naturalistic observation (recording in natural setting without interference), Case study (in-depth analysis of a single unique individual), and Survey method (questionnaires/interviews assessing self-reported attitudes).
   • Correlational Research: Measures statistical association between two variables using correlation coefficient r (-1.00 ≤ r ≤ +1.00). Positive correlation (both move in same direction), negative correlation (inverse movement). Crucial rule: Correlation does NOT prove causation.
   • Experimental Research: The ONLY method that demonstrates cause-and-effect relationships.
     - Independent Variable (IV): Factor manipulated by the experimenter.
     - Dependent Variable (DV): Measured response outcome influenced by IV.
     - Experimental Group: Receives the treatment/manipulation.
     - Control Group: Receives no treatment (or placebo), serving as comparison baseline.
     - Random Assignment: Ensures each participant has equal chance of placement in any group, minimizing confounding variables.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Remember: Wilhelm Wundt established the first formal psychology laboratory in 1879 in Leipzig, Germany.
• A correlation coefficient of -0.85 is STRONGER than +0.70. Sign (+/-) indicates direction; absolute value indicates strength!
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 76
    ),
    ShortNote(
        id = "psyc-u2",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 2",
        title = "Biological Basis of Behavior & Brain Structures",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
All human behavior, emotion, and thought originate from biological processes governed by the nervous and endocrine systems. The brain, with approximately 86 billion neurons, coordinates complex human functioning.

🔍 DETAILED BREAKDOWN
1. Anatomy and Function of the Neuron:
   • Dendrites: Branching extensions that receive incoming electrochemical signals from adjacent neurons.
   • Soma (Cell Body): Contains nucleus, maintains metabolic life of cell, and integrates incoming signals.
   • Axon: Long fiber that transmits electrical impulses (action potentials) away from soma toward terminal buttons.
   • Myelin Sheath: Fatty insulating glial layer accelerating conduction speed along axon via nodes of Ranvier (saltatory conduction). Degradation causes Multiple Sclerosis.
   • Terminal Buttons / Synaptic Knobs: Contain synaptic vesicles that release neurotransmitters across the synaptic cleft.

2. Neural Transmission & Action Potential:
   • Resting Potential: Neuron's electrical charge when inactive (~ -70 mV, interior negative relative to extracellular fluid due to Na⁺/K⁺ pump).
   • Action Potential: Rapid depolarization triggered when threshold (~ -55 mV) is reached; voltage-gated Na⁺ channels open, rushing sodium into axon, peaking at +30 to +40 mV.
   • All-or-None Law: Neuron fires with complete magnitude or does not fire at all.
   • Synapse: Microscopic gap between presynaptic axon and postsynaptic dendrite. Neurotransmitters bind to specific receptor sites like a lock and key, followed by reuptake or enzymatic degradation.

3. Key Neurotransmitters:
   • Acetylcholine (ACh): Muscle contractions, learning, memory. Deficit strongly linked to Alzheimer's disease.
   • Dopamine: Reward circuits, motor control, pleasure. Excess linked to Schizophrenia; deficit causes Parkinson's disease.
   • Serotonin: Mood regulation, sleep cycles, appetite. Deficit linked to Clinical Depression.
   • Norepinephrine: Arousal, fight-or-flight response, vigilance.
   • GABA (Gamma-Aminobutyric Acid): Primary inhibitory neurotransmitter; dampens neural excitability. Deficit causes severe anxiety/seizures.
   • Glutamate: Primary excitatory neurotransmitter; vital for synaptic plasticity and memory.
   • Endorphins: Natural opiate-like pain relievers and mood elevators.

4. Divisions of the Nervous System:
   • Central Nervous System (CNS): Brain and Spinal Cord (spinal reflex arcs operate without prior brain intervention).
   • Peripheral Nervous System (PNS):
     - Somatic Nervous System: Controls voluntary movements of skeletal muscles.
     - Autonomic Nervous System (ANS): Controls involuntary automatic visceral organs.
       * Sympathetic Division: "Fight-or-Flight" (dilates pupils, accelerates heart, inhibits digestion, releases adrenaline).
       * Parasympathetic Division: "Rest-and-Digest" (calms body, slows heart, stimulates digestion, conserves energy).

5. Cerebral Cortex Lobes & Subcortical Systems:
   • Frontal Lobe: Motor cortex, planning, reasoning, executive decision-making. Houses Broca's Area (speech production - damage causes Broca's expressive aphasia).
   • Parietal Lobe: Somatosensory cortex (processes touch, pressure, temperature, body position).
   • Occipital Lobe: Primary visual cortex (processes visual information).
   • Temporal Lobe: Primary auditory cortex, houses Wernicke's Area (language comprehension - damage causes fluent but meaningless speech).
   • Limbic System: Amygdala (fear, aggression, emotional processing), Hippocampus (formation and consolidation of new long-term declarative memories), Hypothalamus (homeostatic regulation: hunger, thirst, temperature, endocrine control).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Broca's area is in the Frontal lobe (speech production), while Wernicke's area is in the Temporal lobe (language comprehension).
• Sympathetic = energy expenditure in emergencies; Parasympathetic = restoration and conservation.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 82
    ),
    ShortNote(
        id = "psyc-u3",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 3",
        title = "Sensation, Perception & Gestalt Organization",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Sensation is the physiological process of detecting environmental energy via sensory receptors. Perception is the psychological cognitive process of organizing, identifying, and interpreting sensory data to understand the external environment.

🔍 DETAILED BREAKDOWN
1. Fundamentals of Psychophysics:
   • Transduction: Conversion of physical energy (light photons, sound waves) into electro-chemical neural impulses.
   • Absolute Threshold: Minimum stimulus intensity detectable 50% of the time (e.g., candle flame seen at 30 miles on dark clear night).
   • Difference Threshold / Just Noticeable Difference (JND): Minimum difference between two stimuli detectable 50% of the time.
   • Weber's Law: The just noticeable difference is a constant proportion (k) of the original stimulus intensity: ΔI / I = k. (Heavier weights require larger absolute differences to notice a change).
   • Sensory Adaptation: Diminished sensitivity to constant, unchanging stimulation (e.g., odor in a room becomes imperceptible after prolonged exposure).

2. Signal Detection Theory:
   • Rejects fixed absolute thresholds. Assumes stimulus detection depends on both stimulus strength and psychological state (expectation, fatigue, motivation). Four outcomes: Hit, Miss, False Alarm, Correct Rejection.

3. Gestalt Principles of Perceptual Organization:
   • Figure-Ground Relationship: Visual system automatically separates the focal object (figure) from background surroundings (ground).
   • Law of Proximity: Objects placed close together are perceived as belonging to the same group.
   • Law of Similarity: Visual items sharing physical characteristics (color, shape, size) are grouped together.
   • Law of Continuity: Preference to perceive smooth, continuous flowing lines rather than abrupt, jagged changes.
   • Law of Closure: The mind automatically fills in missing gaps in incomplete figures to perceive a complete, enclosed whole.
   • Law of Simplicity (Prägnanz): Visual stimulus is organized into the simplest, most stable possible interpretation.

4. Depth Perception:
   • Monocular Cues (requires one eye):
     - Linear Perspective: Parallel lines appear to converge in distance.
     - Relative Size: Smaller images on retina are perceived as farther away.
     - Interposition (Overlap): Object partially blocking another is perceived as closer.
     - Texture Gradient: Coarse detailed textures indicate proximity; smooth dense textures indicate distance.
     - Motion Parallax: Nearby objects appear to move backward rapidly; distant objects appear stationary or move slowly forward.
   • Binocular Cues (requires both eyes):
     - Retinal Disparity: Slight difference between retinal images seen by left and right eyes due to eye separation; greater disparity signifies closer object.
     - Convergence: Inward muscular rotation of eyes when focusing on nearby objects.

5. Perceptual Constancies:
   • Tendency to perceive objects as unchanging despite shifting sensory inputs on retina:
     - Size Constancy (person walking away perceived as moving, not shrinking).
     - Shape Constancy (door opening perceived as maintaining rectangular shape, despite trapezoidal retinal projection).
     - Color & Brightness Constancy.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Top-down processing is driven by past experience, expectations, and context (concept-driven), whereas bottom-up processing begins with raw sensory inputs (data-driven).
• Remember: Sensory receptors do not adapt to pain as rapidly or completely as they do to smells, sounds, or touch.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 79
    ),
    ShortNote(
        id = "psyc-u4",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 4",
        title = "Theories & Principles of Learning",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Learning is defined as any relatively permanent change in behavior, knowledge, or capability resulting from experience, practice, or environmental interaction. It excludes behavioral changes caused by maturation, illness, fatigue, or drugs.

🔍 DETAILED BREAKDOWN
1. Classical Conditioning (Ivan Pavlov - Learning by Association):
   • Core Components:
     - Unconditioned Stimulus (UCS): Naturally triggers reflex without prior training (e.g., food/meat powder).
     - Unconditioned Response (UCR): Involuntary innate reflex to UCS (e.g., salivation to food).
     - Conditioned Stimulus (CS): Previously neutral stimulus that, through repeated pairing with UCS, triggers conditioned response (e.g., bell/tuning fork).
     - Conditioned Response (CR): Learned response to previously neutral CS (e.g., salivation to bell).
   • Conditioning Phenomena:
     - Acquisition: Initial learning stage where CS is paired with UCS.
     - Extinction: Gradual weakening and disappearance of CR when CS is presented repeatedly without UCS.
     - Spontaneous Recovery: Sudden reappearance of an extinguished CR after a rest period.
     - Stimulus Generalization: Responding to stimuli similar to the original CS (e.g., salivating to a buzzer of similar pitch).
     - Stimulus Discrimination: Learning to respond exclusively to specific CS while withholding response to different stimuli.
     - Little Albert Experiment (John Watson): Conditioned fear of white rats paired with loud noise, demonstrating emotional conditioning.

2. Operant Conditioning (B.F. Skinner & Edward Thorndike - Learning by Consequences):
   • Thorndike's Law of Effect: Responses followed by satisfying outcomes are strengthened; responses followed by unpleasant outcomes are weakened.
   • Four Behavioral Contingencies:
     - Positive Reinforcement: Adding a desirable stimulus to INCREASE target behavior (e.g., praise, bonus).
     - Negative Reinforcement: Removing or avoiding an unpleasant/aversive stimulus to INCREASE target behavior (e.g., putting on seatbelt to stop annoying beep; taking aspirin to relieve headache).
     - Positive Punishment: Adding an unpleasant stimulus to DECREASE target behavior (e.g., speeding ticket, reprimand).
     - Negative Punishment (Penalty): Removing a pleasant/valued stimulus to DECREASE target behavior (e.g., revoking phone privileges, grounding).
   • Schedules of Reinforcement:
     - Continuous Reinforcement: Reinforcing every response (best for initial acquisition).
     - Fixed-Ratio (FR): Reinforcement after a set number of responses (e.g., paid every 10 garments produced).
     - Variable-Ratio (VR): Reinforcement after an unpredictable number of responses (e.g., gambling, slot machines). Produces highest response rates and highest resistance to extinction!
     - Fixed-Interval (FI): Reinforcement for first response after a set time period (e.g., cramming before weekly exams; scalloped curve).
     - Variable-Interval (VI): Reinforcement after unpredictable time intervals (e.g., unannounced pop quizzes).
   • Shaping: Reinforcing successive approximations of a desired complex behavior until final target is achieved.

3. Social Learning / Observational Learning (Albert Bandura):
   • Asserts that learning occurs without direct reinforcement by observing models (vicarious learning).
   • Classic Bobo Doll Study: Children exposed to aggressive adult models imitated aggressive actions toward the doll.
   • Four Essential Mediating Cognitive Processes:
     1. Attention: Noticing and focusing on the model's behavior.
     2. Retention: Storing mental representation of observed behavior in memory.
     3. Motor Reproduction: Physical capability of executing the stored behavior.
     4. Motivation / Reinforcement: Expectation of reward, incentive, or anticipated consequences.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Negative reinforcement is NOT punishment! Reinforcement ALWAYS increases behavior; punishment ALWAYS decreases behavior. Negative simply means removing a stimulus.
• Variable-Ratio schedule exhibits the highest resistance to extinction in operant conditioning.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 93
    ),
    ShortNote(
        id = "psyc-u5",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 5",
        title = "Memory Systems, Retrieval & Theories of Forgetting",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Memory is the cognitive capacity to encode, store, and retrieve information. The multi-store information processing model provides the conceptual architecture for understanding how information flows through sensory, working, and long-term memory.

🔍 DETAILED BREAKDOWN
1. The Three Memory Stages (Atkinson-Shiffrin Model):
   • Sensory Memory:
     - Brief preservation of sensory impressions in raw, unprocessed sensory registers.
     - Iconic Memory: Visual sensory memory lasting ~0.5 seconds.
     - Echoic Memory: Auditory sensory memory lasting ~3 to 4 seconds.
   • Short-Term Memory (STM) & Working Memory:
     - Active workspace holding consciously processed information.
     - Duration: ~20 to 30 seconds without maintenance rehearsal.
     - Capacity: George Miller's magical number 7 ± 2 items (modern working memory estimates ~4 ± 1 items).
     - Chunking: Grouping individual bits into meaningful units (e.g., phone numbers 0911-23-45-67) to expand capacity.
     - Alan Baddeley's Working Memory Model: Central Executive (coordinates attention), Phonological Loop (verbal/auditory rehearsal), Visuospatial Sketchpad (visual images), Episodic Buffer (integrates into coherent sequence).
   • Long-Term Memory (LTM):
     - Permanent, unlimited capacity storage system.
     - Elaborative Rehearsal: Deep processing by connecting new material with existing knowledge (Craik & Lockhart's Levels of Processing).

2. Dual Architecture of Long-Term Memory:
   • Explicit / Declarative Memory (Conscious, intentional recollection):
     - Semantic Memory: General factual knowledge about concepts, words, rules, and logic (e.g., "The capital of Ethiopia is Addis Ababa").
     - Episodic Memory: Autobiographical memories of personally experienced events tied to specific times and places (e.g., "My first day at university").
   • Implicit / Non-Declarative Memory (Automatic, unconscious recall):
     - Procedural Memory: Motor skills, physical habits, learned reflexes (e.g., riding a bicycle, typing, swimming).
     - Priming: Enhanced identification of objects/words based on prior exposure.

3. Retrieval Processes & Context Effects:
   • Recall: Retrieving information without external cues (e.g., essay questions).
   • Recognition: Identifying previously learned material when presented among alternatives (e.g., multiple choice exams). Recognition is typically easier than recall.
   • Serial Position Effect: Tendency to recall first items (Primacy Effect - stored in LTM) and last items (Recency Effect - still in STM) better than middle items.
   • Encoding Specificity Principle: Retrieval is superior when external cues match conditions during encoding (Context-dependent and State-dependent memory).

4. Theories of Forgetting:
   • Decay Theory: Memory traces (engrams) in the brain fade passively over time if not refreshed.
   • Interference Theory:
     - Proactive Interference: OLD learning disrupts and interferes with retrieval of NEW information (e.g., repeatedly calling new teacher by previous teacher's name).
     - Retroactive Interference: NEW learning interferes with retrieval of OLD information (e.g., learning new phone number causes you to forget your childhood phone number).
   • Retrieval Failure: Information is in LTM but cannot be accessed due to insufficient retrieval cues (e.g., Tip-of-the-Tongue phenomenon).
   • Motivated Forgetting (Freudian Repression): Defense mechanism unconsciously blocking traumatic or painful memories from conscious awareness.
   • Amnesia: Anterograde Amnesia (inability to form new long-term memories post-injury; damage to hippocampus, e.g., Patient H.M.); Retrograde Amnesia (loss of memories formed prior to brain trauma).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Proactive vs Retroactive interference mnemonic: PORN (Proactive = Old interferes with new; Retroactive = New interferes with old).
• The Hippocampus consolidates explicit declarative memories, while the Cerebellum and Basal Ganglia handle implicit procedural memories.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 87
    ),
    ShortNote(
        id = "psyc-u6",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 6",
        title = "Motivation, Drive-Reduction, Maslow's Hierarchy & Emotion Theories",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Motivation refers to internal physiological states and psychological forces that initiate, direct, and sustain goal-directed human action. Emotion is a complex feeling state involving physiological arousal, expressive behavior, and cognitive appraisal.

🔍 DETAILED BREAKDOWN
1. Theories of Motivation & Biological Drives:
   • Drive-Reduction Theory (Clark Hull): Biological physiological needs (food, water) produce psychological tension states called drives. The organism is motivated to reduce tension to restore Homeostasis (internal biological equilibrium).
     - Primary Drives: Unlearned, innate biological necessities (hunger, thirst, sleep, sex).
     - Secondary (Acquired) Drives: Learned through conditioning and association (money, academic grades, social approval).
   • Arousal Theory & Yerkes-Dodson Law: Organisms seek an optimal level of arousal, not minimal arousal.
     - Performance increases with arousal up to an optimal midpoint, beyond which performance deteriorates (inverted-U curve).
     - Task Difficulty Rule: Simple, well-practiced tasks are best performed under higher arousal; complex, difficult cognitive tasks require lower arousal.
   • Incentive Theory: Emphasizes the "pull" of external environmental stimuli and rewards (e.g., aroma of fresh bread), contrasting with the internal "push" of drives.
   • Intrinsic vs Extrinsic Motivation:
     - Intrinsic: Engaging in an activity purely for inherent personal enjoyment, curiosity, or mastery.
     - Extrinsic: Performing an activity to attain external rewards or avoid punishment.
     - Overjustification Effect: Introducing unexpected extrinsic tangible rewards for already intrinsically motivating tasks can paradoxically erode intrinsic interest.

2. Abraham Maslow's Hierarchy of Needs:
   • Humanistic Model arranging human motivation in hierarchical pyramid:
     1. Physiological Needs: Base tier; survival necessities (food, water, shelter, oxygen).
     2. Safety & Security: Physical stability, order, employment, health, legal protection.
     3. Love & Belongingness: Social integration, friendship, family, intimate relationships.
     4. Esteem Needs: Self-respect, competence, status, prestige, recognition from others.
     5. Self-Actualization: Peak pinnacle; realizing one's fullest unique human potential and creative capacities.
   • Distinction: Stages 1–4 are Deficiency Needs (D-needs: arise due to deprivation); Stage 5 is a Growth / Being Need (B-need).

3. Anatomy & Tripartite Components of Emotion:
   • Emotion vs Mood: Emotions are intense, acute, stimulus-specific responses; moods are milder, generalized, longer-lasting emotional climates lacking clear trigger.
   • Three Integrated Dimensions:
     1. Physiological Arousal: Regulated by Autonomic Nervous System (Sympathetic branch triggers fight-or-flight; Parasympathetic restores calm) and the Amygdala (evaluates threat and fear).
     2. Expressive Behavior: Observable non-verbal signals, gestures, and facial expressions. Paul Ekman identified six universally recognized facial expressions across all human cultures: Happiness, Sadness, Anger, Fear, Surprise, and Disgust.
     3. Conscious Cognitive Experience: Subjective interpretation, labeling, and personal meaning attributed to the event.

4. Major Classical Theories of Emotion:
   • James-Lange Theory: Physiological autonomic arousal PRECEDES and causes emotional feeling.
     - Sequence: Stimulus -> Physiological Arousal -> Emotional Feeling ("We feel afraid because we tremble; we feel sorry because we cry").
   • Cannon-Bard Theory: Arousal and emotional experience occur SIMULTANEOUSLY and independently via the Thalamus.
     - Sequence: Stimulus -> Thalamus sends simultaneous signals to Cortex (conscious feeling) and Autonomic Nervous System (bodily arousal).
   • Schachter-Singer Two-Factor Theory: Emotion requires BOTH physiological arousal AND cognitive interpretation/labeling based on environmental context.
     - Sequence: Stimulus -> General Physiological Arousal -> Cognitive Appraisal of Context -> Distinct Emotional State.
   • Lazarus Cognitive-Mediational Theory: Cognitive appraisal occurs FIRST, preceding both autonomic arousal and emotional response.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The key differentiator between emotion theories: James-Lange says arousal comes first; Cannon-Bard says arousal and emotion happen simultaneously; Schachter-Singer requires cognitive labeling of the arousal.
• Under the Yerkes-Dodson Law, complex academic exams require LOW to MODERATE arousal for optimal concentration and recall.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 92
    ),
    ShortNote(
        id = "psyc-u7",
        subjectId = "c3",
        subject = "General Psychology",
        unit = "Unit 7",
        title = "Personality Theories, Psychological Disorders & Coping Mechanisms",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Personality encompasses enduring, distinctive patterns of thinking, feeling, and behaving that characterize an individual's adaptation to life. Abnormal psychology classifies mental disorders, while health psychology explores stress, coping, and mental hygiene.

🔍 DETAILED BREAKDOWN
1. Psychoanalytic Perspective on Personality (Sigmund Freud):
   • Structural Model of the Psyche:
     - Id: Entirely unconscious; operates on the Pleasure Principle, demanding immediate gratification of instinctual impulses (Eros: life instinct; Thanatos: death/aggressive instinct).
     - Ego: Operates on the Reality Principle; conscious executive that mediates realistically between the unreasonable demands of the Id, Superego, and external reality.
     - Superego: Moral compass; internalizes parental and cultural ethical standards, inducing guilt and striving for perfection.
   • Ego Defense Mechanisms (Unconscious protective strategies against anxiety):
     - Repression: Banishing threatening or traumatic memories from conscious awareness.
     - Projection: Attributing one's own unacceptable impulses or flaws onto other individuals.
     - Rationalization: Formulating comforting, logical excuses to justify unacceptable behaviors.
     - Reaction Formation: Transforming unacceptable impulses into their exact opposite extreme.
     - Displacement: Redirecting emotional or aggressive reactions from original threatening target onto safer substitute.
     - Sublimation: Channeling socially unacceptable drives into constructive, highly valued societal outputs (art, sports).
     - Regression: Reverting to immature behaviors typical of an earlier developmental stage under acute stress.

2. Trait Theories & The Big Five Model:
   • Gordon Allport's Hierarchy: Cardinal traits (rare dominant traits shaping whole life), Central traits (core building blocks of personality), Secondary traits (situational preferences).
   • Five-Factor Model (The Big Five / OCEAN mnemonic):
     - Openness to Experience: Intellectual curiosity, imagination, aesthetic appreciation vs pragmatic, routine-bound.
     - Conscientiousness: Self-discipline, organized, dependable, goal-directed vs careless, spontaneous.
     - Extraversion: Outgoing, enthusiastic, assertive, socially energized vs reserved, solitary (introverted).
     - Agreeableness: Compassionate, cooperative, empathetic, trusting vs cynical, competitive, hostile.
     - Neuroticism: Emotional instability, anxiety, irritability, vulnerability to distress vs calm, emotionally resilient.

3. Psychological Disorders (Psychopathology Criteria):
   • The 4 Ds of Abnormality: Deviance (statistically or culturally atypical), Distress (personal emotional pain), Dysfunction (impairment in daily social/occupational roles), Danger (threat to self or others).
   • Major Diagnostic Categories:
     - Anxiety Disorders: Generalized Anxiety Disorder (persistent chronic worry), Panic Disorder (sudden episodes of terror), Phobias (irrational fear of specific objects/situations).
     - Obsessive-Compulsive Disorder (OCD): Recurrent intrusive thoughts (obsessions) generating anxiety relieved by ritualistic repetitive actions (compulsions).
     - Mood Disorders: Major Depressive Disorder (prolonged depressed mood, anhedonia, fatigue, feelings of worthlessness) vs Bipolar Disorder (fluctuations between major depression and manic hyperactivity).
     - Schizophrenia: Severe psychotic disorder characterized by Positive Symptoms (hallucinations - mainly auditory, delusions of grandeur or persecution) and Negative Symptoms (flat affect, avolition, social withdrawal).

4. Stress, Hans Selye's GAS & Coping Strategies:
   • General Adaptation Syndrome (GAS): Body's universal response to chronic stress in 3 phases:
     1. Alarm Reaction: Immediate fight-or-flight activation; sympathetic nervous system releases adrenaline/noradrenaline.
     2. Resistance: Body attempts physiological adaptation to ongoing stress; adrenal cortex secretes cortisol.
     3. Exhaustion: Depletion of bodily physical resources, immune suppression, high susceptibility to chronic illnesses.
   • Coping Strategies (Lazarus & Folkman):
     - Problem-Focused Coping: Directly confronting and solving the root cause of stress (time management, study schedules).
     - Emotion-Focused Coping: Regulating and managing emotional distress associated with stressor (reframing, seeking emotional support, mindfulness).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Sublimation is considered the most mature and constructive defense mechanism in psychoanalysis.
• In GAS, exhaustion phase occurs when bodily reserves are depleted following chronic prolonged exposure to stress.
        """.trimIndent(),
        colorHex = 0xFF0D9488,
        initialLikes = 96
    ),

    // =========================================================================
    // 4. LOGIC & CRITICAL THINKING (c4, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "logic-u1",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 1",
        title = "Philosophy, Meaning & Critical Thinking Foundations",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Philosophy originates from the Greek words "Philo" (love) and "Sophia" (wisdom) — the systematic inquiry into fundamental questions regarding reality, knowledge, values, and reasoning. Critical thinking is disciplined, self-directed, rational evaluation of beliefs.

🔍 DETAILED BREAKDOWN
1. Core Characteristics of Philosophical Inquiry:
   • Highly critical, systematic, radical, foundational, comprehensive, and non-dogmatic.
   • Examines basic presuppositions that ordinary science takes for granted (e.g., What is truth? What is justice?).

2. Major Branches of Philosophy:
   • Metaphysics: Study of ultimate reality and the nature of existence.
     - Ontology: Study of being and existence as such.
     - Cosmology: Study of the origin and structure of the universe.
   • Epistemology: Theory of knowledge, its nature, sources, scope, and validity.
     - Rationalism: Reason is the primary source of knowledge (Descartes, Spinoza, Leibniz).
     - Empiricism: Sensory experience is the primary source of knowledge (Locke, Berkeley, Hume).
   • Axiology: Philosophical study of value and valuation.
     - Ethics (Moral Philosophy): Investigates moral values, right vs wrong, good vs bad conduct.
     - Aesthetics: Study of beauty, art, taste, and sublime experiences.
   • Logic: Study of correct reasoning, valid argumentation, and methods for evaluating arguments.

3. Critical Thinking & Intellectual Virtues:
   • Critical thinking is the disciplined cognitive process of actively evaluating information, arguments, and beliefs without bias.
   • Core Intellectual Virtues: Intellectual humility, courage, empathy, autonomy, integrity, perseverance, and confidence in reason.
   • Barriers to Critical Thinking: Egocentrism (self-centered thinking), Sociocentrism (group bias, conformism), Stereotyping, Relativistic thinking, and Wishful thinking.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Metaphysics asks "What is real?", Epistemology asks "How do we know?", and Axiology asks "What is of value?".
• Critical thinking is not negative or cynical; it is constructive, objective, and evidence-grounded evaluation.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 75
    ),
    ShortNote(
        id = "logic-u2",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 2",
        title = "Basic Concepts of Arguments (Deduction, Induction, Validity & Soundness)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Logic evaluates arguments. An argument is a structured group of statements where one or more premises claim to provide evidentiary support or reasons to believe a conclusion.

🔍 DETAILED BREAKDOWN
1. Structure of an Argument:
   • Statement (Proposition): A declarative sentence that is either true or false (Truth Value). Commands, questions, and exclamations are NOT statements.
   • Premises: Statements setting forth evidence, reasons, or grounds.
   • Conclusion: The claim supported by the premises.
   • Premise Indicators: Since, because, for, as, given that, for the reason that, inasmuch as.
   • Conclusion Indicators: Therefore, thus, hence, consequently, so, it follows that, implies that.

2. Recognizing Non-Arguments:
   • Non-inferential passages lack the claim that one statement proves another:
     - Reports, warnings, pieces of advice, expressions of belief/opinion.
     - Conditional Statements ("If P, then Q"): Not arguments by themselves, though they can be premises within arguments.
     - Explanations: Contain an explanandum (the accepted fact being explained) and an explanans (the claim explaining why it occurred). Unlike arguments, explanations do not aim to prove THAT something happened.

3. Deductive Arguments:
   • Claim: Conclusion is claimed to follow with strict NECESSITY from premises.
   • Validity: A deductive argument is Valid if it is impossible for the premises to be true and the conclusion simultaneously false. Validity depends purely on logical form, NOT factual truth!
   • Invalid: An argument where it is possible for true premises to yield a false conclusion.
   • Soundness: An argument is Sound if and only if it is (1) Valid AND (2) possesses all factual TRUE premises. If either condition fails, it is Unsound.
   • Deductive Forms: Arguments based on mathematics, definitions, categorical syllogisms, hypothetical syllogisms, disjunctive syllogisms.

4. Inductive Arguments:
   • Claim: Conclusion is claimed to follow only with PROBABILITY from premises.
   • Strength: An inductive argument is Strong if, assuming premises are true, it is probable (more than 50% chance) that the conclusion is true. Otherwise it is Weak.
   • Cogency: An inductive argument is Cogent if and only if it is (1) Strong AND (2) has all true premises (meeting the total evidence requirement). If weak or having false premises, it is Uncogent.
   • Inductive Forms: Inductive generalizations, predictive arguments, arguments from analogy, causal inferences, arguments from authority.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• True premises + True conclusion does NOT automatically mean valid! Check if conclusion NECESSARILY follows.
• Only one combination is strictly forbidden for valid arguments: True premises leading to a False conclusion. If this occurs, the argument is guaranteed to be INVALID.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 89
    ),
    ShortNote(
        id = "logic-u3",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 3",
        title = "Logic & Language, Definitions & Terminology",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Language serves multiple functions. Clear reasoning requires resolving vagueness, ambiguity, and emotional coloring through precise definitions and intentional/extensional analysis of terms.

🔍 DETAILED BREAKDOWN
1. Functions of Language:
   • Cognitive (Informative) Function: Communicates factual objective information, descriptions, and arguments. Evaluated as true or false. Logic primarily analyzes cognitive language.
   • Emotive Function: Expresses or evokes feelings, attitudes, and emotional responses.
   • Directive Function: Commands, requests, or directs behavior.
   • Performative Function: Actions performed through speech (e.g., "I promise", "I now pronounce you husband and wife").

2. Meaning of Terms (Intension vs Extension):
   • Term: A word or phrase capable of serving as the subject of a proposition (proper names, common names, descriptive phrases).
   • Intensional Meaning (Connotation): The set of attributes, qualities, or characteristics that determine what a term refers to (e.g., intension of "tiger" = large, carnivorous, striped feline).
   • Extensional Meaning (Denotation): The actual individual members or objects of the class referred to by the term (e.g., denotation of "African country" = Ethiopia, Kenya, Nigeria, etc.).
   • Law of Inverse Variation: As intension increases (more specific attributes added), extension decreases (fewer objects qualify).

3. Types of Definitions:
   • Stipulative Definition: Assigns a brand new meaning to a newly coined term (or repurposed word). Cannot be true or false. (e.g., selfie, blog).
   • Lexical Definition: Reports established, standard dictionary meaning already in use. Can be true or false. Eliminates ambiguity.
   • Precising Definition: Reduces the vagueness of an existing term for a specific specialized context (e.g., defining "poverty line" as household income below $2/day for aid eligibility).
   • Theoretical Definition: Formulates an intellectually adequate or scientifically rigorous description of the nature of a phenomenon (e.g., "Light is electromagnetic radiation").
   • Persuasive Definition: Formulated using emotionally charged or biased terms to influence attitudes (e.g., "Abortion is the murder of innocent fetuses").

4. Definitional Techniques:
   • Extensional (Denotative) Techniques: Demonstrative (ostensive pointing), Enumerative (naming individual members), Definition by Subclass.
   • Intensional (Connotative) Techniques: Synonymous definition, Etymological definition (root origins), Operational definition (specifying testing procedure), Definition by Genus and Difference (identifies broader class and differentiating attribute).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Vagueness means borderline cases exist where application is uncertain; Ambiguity means a term has two or more completely distinct meanings in context.
• A definition by Genus and Difference consists of Genus (larger category) + Specific Difference (unique feature that sets it apart).
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 80
    ),
    ShortNote(
        id = "logic-u4",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 4",
        title = "Informal Fallacies (Relevance, Weak Induction & Presumption)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
A fallacy is a defect in an argument that arises from faulty reasoning rather than mere factual falsehood. Informal fallacies occur due to defects in content, meaning, or context.

🔍 DETAILED BREAKDOWN
1. Fallacies of Relevance (Premises are logically irrelevant to conclusion):
   • Appeal to Force (Argumentum ad Baculum): Threatening harm or coercion to force acceptance of a conclusion.
   • Appeal to Pity (Argumentum ad Misericordiam): Evoking sympathy or pity to secure agreement instead of presenting evidence.
   • Appeal to the People (Argumentum ad Populum): Appealing to popular desires, bandwagon effect, vanity, or elitism ("Everyone uses it, so you should too").
   • Argument Against the Person (Argumentum ad Hominem): Attacking the opponent's personal traits rather than addressing their argument:
     - Ad Hominem Abusive: Direct personal verbal insult.
     - Ad Hominem Circumstantial: Attacking opponent's personal circumstances, interests, or employment.
     - Tu Quoque ("You too"): Accusing the opponent of hypocrisy to discredit their claim.
   • Accident: Erroneously applying a general rule to an atypical specific case where it does not fit.
   • Straw Man: Distorting, exaggerating, or misrepresenting an opponent's argument to knock it down easily.
   • Missing the Point (Ignoratio Elenchi): Presenting premises that support one specific conclusion, but drawing a completely different, unjustified conclusion.
   • Red Herring: Diverting attention away from the original issue by introducing an exciting, irrelevant side topic.

2. Fallacies of Weak Induction (Premises provide insufficient probabilistic evidence):
   • Appeal to Unqualified Authority (Ad Verecundiam): Citing a source that lacks expertise in the field under discussion.
   • Appeal to Ignorance (Ad Ignorantiam): Claiming a statement is true because nobody has proven it false (or vice-versa).
   • Hasty Generalization: Drawing a broad conclusion about an entire group based on an unrepresentative or too small sample.
   • False Cause: Improperly concluding causal link:
     - Post hoc ergo propter hoc ("After this, therefore because of this"): Assuming temporal succession implies causation.
     - Non causa pro causa: Mistaking what is not the cause for the cause.
     - Oversimplified Cause: Selecting one factor when multiple causes exist.
   • Slippery Slope: Asserting without evidence that an initial harmless step will inevitably lead to an uncontrolled chain reaction of catastrophe.
   • Weak Analogy: Comparing two things that share superficial similarities while ignoring critical relevant differences.

3. Fallacies of Presumption, Ambiguity & Grammatical Analogy:
   • Begging the Question (Petitio Principii): Assuming the truth of the conclusion within the premises (circular reasoning).
   • False Dichotomy: Presenting two alternatives as exhaustive when other reasonable possibilities exist ("Either you are with us or you are against us").
   • Equivocation: Shifting the meaning of a key ambiguous word or phrase across the argument.
   • Amphiboly: Ambiguity caused by faulty sentence grammar or punctuation.
   • Composition: Erroneously transferring an attribute of individual parts to the whole system.
   • Division: Erroneously transferring an attribute of the whole system to its individual parts.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Straw Man vs Red Herring: Straw Man distorts the OPPONENT'S argument; Red Herring changes the TOPIC entirely.
• Composition: "Each atom is invisible, therefore the elephant is invisible." Division: "The university is 100 years old, therefore each professor is 100 years old."
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 98
    ),
    ShortNote(
        id = "logic-u5",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 5",
        title = "Categorical Propositions, Square of Opposition & Syllogisms",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Categorical propositions assert or deny that all or some members of one category (Subject term) belong to another category (Predicate term). They form the backbone of Aristotelian deductive logic.

🔍 DETAILED BREAKDOWN
1. Four Standard-Form Categorical Propositions:
   • A (Universal Affirmative): "All S are P"
     - Quantity: Universal | Quality: Affirmative | Distributed term: Subject (S) only.
   • E (Universal Negative): "No S are P"
     - Quantity: Universal | Quality: Negative | Distributed terms: BOTH Subject (S) and Predicate (P).
   • I (Particular Affirmative): "Some S are P"
     - Quantity: Particular | Quality: Affirmative | Distributed terms: NEITHER.
   • O (Particular Negative): "Some S are not P"
     - Quantity: Particular | Quality: Negative | Distributed term: Predicate (P) only.
   • Mnemonic for Distribution: "Unconditionally Distribute Subject in Universals, Predicate in Negatives" (Any Student Is Puzzled).

2. Traditional (Aristotelian) Square of Opposition:
   • Contradictories (A & O, E & I): Opposite truth values. If one is true, the other MUST be false; they cannot both be true and cannot both be false.
   • Contraries (A & E): Cannot both be true simultaneously (at least one is false). If one is true, the other is false; but if one is false, the other is undetermined.
   • Subcontraries (I & O): Cannot both be false simultaneously (at least one is true). If one is false, the other is true; but if one is true, the other is undetermined.
   • Subalternation (A to I, E to O): Truth flows downward (if A is true, I is true); Falsity flows upward (if I is false, A is false).

3. Categorical Syllogisms:
   • Standard Form: Deductive argument consisting of exactly 3 categorical propositions containing exactly 3 distinct terms:
     - Major Term: The predicate of the conclusion.
     - Minor Term: The subject of the conclusion.
     - Middle Term: Appears in both premises but NEVER in the conclusion.
     - Major Premise contains the Major Term; Minor Premise contains the Minor Term.
   • Mood and Figure:
     - Mood: 3-letter sequence of proposition types (e.g., AAA, EIO, EAE).
     - Figure: Position of middle term M (Figure 1: M-P, S-M; Figure 2: P-M, S-M; Figure 3: M-P, M-S; Figure 4: P-M, M-S).

4. Rules of Syllogistic Validity:
   1. The middle term must be distributed in at least one premise (avoids Undistributed Middle).
   2. Any term distributed in the conclusion must be distributed in its premise (avoids Illicit Major / Illicit Minor).
   3. Two negative premises are not allowed (avoids Exclusive Premises).
   4. A negative premise requires a negative conclusion, and vice versa.
   5. Two universal premises cannot yield a particular conclusion from Boolean standpoint (Existential Fallacy).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Contradictory relationship is the most reliable: whenever A is true, O is immediately false, regardless of standpoint.
• Always verify the position of the Middle term when determining the syllogism's figure.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 86
    ),
    ShortNote(
        id = "logic-u6",
        subjectId = "c4",
        subject = "Logic and Critical Thinking",
        unit = "Unit 6",
        title = "Propositional Logic, Truth Tables & Validity Testing",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Propositional (symbolic) logic replaces natural language statements with proposition letters and logical connectives to evaluate the structural validity of arguments without linguistic ambiguity.

🔍 DETAILED BREAKDOWN
1. The Five Truth-Functional Operators:
   • Negation (Tilde `~` or `¬`): Expresses "not", "it is false that". Inverts truth value: ~T = F, ~F = T.
   • Conjunction (Dot `·` or `∧`): Expresses "and", "but", "however", "moreover".
     - Rule: A conjunction is TRUE if and only if BOTH conjuncts are true; otherwise it is false.
   • Disjunction (Wedge `v` or `∨`): Expresses "or", "either... or", "unless".
     - Rule: An inclusive disjunction is FALSE if and only if BOTH disjuncts are false; otherwise true.
   • Conditional (Arrow `→` or horseshoe `⊃`): Expresses "if... then", "implies", "provided that".
     - Components: Antecedent (if-clause p) and Consequent (then-clause q).
     - Rule: A conditional is FALSE in exactly one scenario: when the Antecedent is TRUE and Consequent is FALSE (T → F = F); otherwise it is true.
   • Biconditional (Triple Bar `≡` or `↔`): Expresses "if and only if" (iff).
     - Rule: A biconditional is TRUE when both component statements have the SAME truth value (both true or both false); otherwise false.

2. Truth Table Construction & Statement Classifications:
   • Row Count Formula: For a compound statement with n distinct proposition variables, the truth table requires 2ⁿ rows (e.g., 2 variables = 4 rows; 3 variables = 8 rows).
   • Statement Classifications:
     - Tautology (Logically True): The column under the main connective has ONLY True (T) values across every row.
     - Contradiction (Logically False): The column under the main connective has ONLY False (F) values across every row.
     - Contingent: The column contains at least one True and at least one False value (truth depends on facts).

3. Testing Argument Validity via Truth Tables:
   • Definition of Deductive Validity: An argument is valid if it is structurally impossible for all premises to be true and the conclusion false.
   • Invalidating Row Test:
     - Inspect the truth table for any row where EVERY premise is TRUE, but the conclusion is FALSE.
     - If even ONE such row exists, the argument is INVALID (fallacious).
     - If NO row contains all true premises with a false conclusion, the argument is structurally VALID.
   • Indirect (Shorthand) Truth Table Method:
     - Attempt to force the conclusion to be False while assigning True to all premises. If doing so produces an inescapable logical contradiction, the argument is valid.

4. Logical Equivalences & De Morgan's Laws:
   • De Morgan's Laws:
     - ~(p ∧ q) ≡ (~p ∨ ~q)   ["Not both" is equivalent to "At least one is not"]
     - ~(p ∨ q) ≡ (~p ∧ ~q)   ["Neither" is equivalent to "Both are not"]
   • Material Implication: (p → q) ≡ (~p ∨ q).
   • Contraposition: (p → q) ≡ (~q → ~p) (A conditional is logically equivalent to its contrapositive, but NOT to its converse or inverse!).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The conditional p → q is ONLY false when p is True and q is False. If antecedent p is False, the conditional is AUTOMATICALLY True regardless of q!
• In De Morgan's laws: Distributing the negation flips a conjunction (∧) to a disjunction (∨), and vice versa.
        """.trimIndent(),
        colorHex = 0xFFD97706,
        initialLikes = 91
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
        title = "Study Skills, Listening Strategies & Academic Note-Taking",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Effective study habits and active listening strategies are crucial for university academic success. Listening is an active interpretive cognitive process, distinct from mere physiological hearing.

🔍 DETAILED BREAKDOWN
1. Active vs Passive Listening:
   • Hearing is an involuntary physiological reception of acoustic vibrations.
   • Listening is an active, voluntary cognitive process involving attention, interpretation, evaluation, and responding.
   • Stages of Listening: Receiving (hearing auditory input), Understanding (decoding meaning), Evaluating (judging credibility and bias), Remembering (storing for recall), Responding (providing verbal/nonverbal feedback).

2. Listening Strategies for Academic Lectures:
   • Listening for Gist (Main Idea): Capturing overall core message without getting distracted by supporting details.
   • Listening for Specific Information: Focusing specifically on dates, names, numerical data, or defined terms.
   • Listening for Inferences & Tone: Detecting underlying attitudes, implied conclusions, and rhetorical shifts signaled by vocal intonation and stress.
   • Signpost Words (Lecture Transitions): Indicators signaling structure ("First", "In contrast", "Crucially", "To summarize").

3. Academic Note-Taking Systems:
   • The Cornell Method:
     - Page divided into 3 zones: Cue Column on left (2.5 inches for keywords/questions), Notes Area on right (6 inches for lecture bullets), Summary Area at bottom (2 inches for 2-3 sentence overview).
   • Mind Mapping:
     - Visual diagram placing main central concept in center with thematic sub-branches radiating outward; effective for visual learners.
   • Outline Method:
     - Indented hierarchical structure: Roman numerals (main topics) -> Capital letters (sub-topics) -> Arabic numbers (supporting details).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• In Cornell notes, the Cue Column is completed AFTER the lecture during review, not during live listening.
• Signpost transition markers (e.g., "however", "consequently") signal shifts in arguments that are frequently tested in comprehension exams.
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 72
    ),
    ShortNote(
        id = "eng-u2",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 2",
        title = "Reading Strategies (Skimming, Scanning & Intensive Reading)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
University academics require handling high volumes of research and textbook materials. Employing targeted reading strategies tailored to the reading objective maximizes efficiency and comprehension.

🔍 DETAILED BREAKDOWN
1. Core Reading Techniques:
   • Skimming: Rapid reading to grasp the overall gist or central message of a text.
     - Technique: Read the title, introduction, first sentence of every paragraph (topic sentences), headings, subheadings, and concluding paragraph.
   • Scanning: Rapid eye movement across a page to locate a specific piece of information without reading the surrounding text.
     - Used for: Looking up names, dates, statistical figures, specific formulas, or glossary terms.
   • Intensive Reading: Slow, focused, in-depth analytical reading for complete comprehension, evaluation of arguments, and vocabulary acquisition.
   • Extensive Reading: Broad, pleasurable reading of longer texts (novels, articles) to develop general reading fluency, speed, and language intuition.

2. Context Clues for Vocabulary Acquisition:
   • Definition / Restatement Clues: The unfamiliar word is explicitly defined using words like "is defined as", "means", "or", commas, or dashes.
   • Synonym Clues: Nearby words with similar meanings provide context.
   • Antonym / Contrast Clues: Signaling contrast using words like "unlike", "whereas", "however", "on the contrary".
   • Example Clues: Illustrations following "for instance", "such as", "namely" clarify meaning.

3. Textual Organizational Patterns:
   • Chronological / Sequential: Events ordered by time.
   • Cause and Effect: Examines reasons (causes) and results (effects) using words like "because", "due to", "as a result".
   • Compare and Contrast: Examines similarities and differences using "similarly", "conversely", "in contrast".
   • Problem and Solution: Explores a difficulty and offers remedial solutions.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Remember the difference: Skimming is for general main idea (gist); Scanning is searching for a pre-known specific fact (keyword).
• Always pay attention to transitional adverbs (moreover, nonetheless, therefore) as they indicate relationships between ideas.
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 77
    ),
    ShortNote(
        id = "eng-u3",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 3",
        title = "Academic Vocabulary, Word Formation & Grammar Concord",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Academic English demands precision, formal register, syntactic accuracy, and grammatical agreement (concord). Understanding morphology empowers students to decipher complex terminology.

🔍 DETAILED BREAKDOWN
1. Word Formation Processes:
   • Affixation:
     - Prefixation: Added to beginning of root, altering meaning but rarely part of speech (e.g., un-, dis-, re-, anti-, mis-, hyper-).
     - Suffixation: Added to end of root, usually changing grammatical class (e.g., -ment, -tion, -ness form nouns; -ify, -ize form verbs; -able, -al form adjectives).
   • Compounding: Joining two independent words to form a new compound noun/adjective (e.g., blackboard, database, widespread).
   • Conversion (Zero Derivation): Changing word class without adding an affix (e.g., to email, a run).
   • Blending: Merging parts of two words (e.g., smog = smoke + fog).
   • Acronyms & Initialisms: Pronounced as single words (e.g., UNESCO, AIDS) vs letters sounded individually (e.g., UN, HIV).

2. Subject-Verb Agreement (Concord) Rules:
   • Singular subjects take singular verbs; plural subjects take plural verbs.
   • Intervening Prepositional Phrases: The subject is NOT affected by modifying phrases (e.g., "The list of freshman students *is* complete", NOT *are*).
   • Indefinite Pronouns: Words like each, everyone, someone, anybody, neither, either are strictly SINGULAR and take singular verbs.
   • Correlative Conjunctions (Either...or / Neither...nor / Not only...but also): The verb agrees with the NEAREST subject (e.g., "Neither the teacher nor the students *were* present").
   • Collective Nouns: Singular when acting as unified single entity (e.g., "The committee *has* reached its decision"); plural when acting individually.
   • Plural in form, singular in meaning: Physics, mathematics, news, economics take SINGULAR verbs.

3. Active vs Passive Voice in Academic Register:
   • Active Voice: Subject performs the action (e.g., "The researchers conducted the experiment").
   • Passive Voice: Subject receives the action (e.g., "The experiment *was conducted* by the researchers").
   • Passive voice is favored in scientific and empirical writing to highlight the object/outcome objectively rather than the personal agent.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Watch out for "one of the [plural nouns] who/that": "He is one of the students who *are* working hard" (who refers to students, so verb is plural).
• When using passive voice, always match the auxiliary "to be" with the appropriate tense and subject number.
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 84
    ),
    ShortNote(
        id = "eng-u4",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 4",
        title = "Paragraph Development (Unity, Coherence & Transitions)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
A paragraph is a unified group of related sentences developing a single controlling idea. Academic paragraphs require clear structure, logical progression, and cohesion.

🔍 DETAILED BREAKDOWN
1. Anatomy of an Academic Paragraph:
   • Topic Sentence: Usually the opening sentence; contains the Topic and the Controlling Idea (limiting scope of discussion).
   • Supporting Sentences: Provide concrete evidence, statistics, examples, authoritative citations, and logical explanations developing the topic sentence.
   • Concluding Sentence: Reasserts the main point in fresh words or provides a logical transition to the succeeding paragraph.

2. Essential Qualities of an Effective Paragraph:
   • Unity: Every single sentence in the paragraph directly relates to and supports the single controlling idea. Any off-topic sentence must be deleted.
   • Coherence: Ideas are arranged logically so the reader follows the reasoning effortlessly.
     - Logical Orders: Chronological (time order), Spatial (physical layout), Emphatic (least to most important, or vice-versa).
   • Cohesion: Mechanical linguistic ties holding sentences together:
     - Pronoun Reference: Using pronouns (it, they, this) to refer back to antecedents without ambiguity.
     - Repetition of Key Terms: Maintaining focus on central concepts.
     - Transitional Words / Cohesive Devices: Addition (furthermore, moreover), Contrast (however, on the other hand), Cause/Result (consequently, therefore, thus), Exemplification (for example, specifically).

3. Common Methods of Paragraph Development:
   • Exemplification / Illustration: Using relevant real-world examples.
   • Cause and Effect: Detailing reasons and subsequent results.
   • Comparison and Contrast: Highlighting similarities and differences (Point-by-Point or Block method).
   • Classification & Division: Grouping concepts into categories.
   • Process Analysis: Step-by-step sequential instructions.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• A topic sentence must NOT be a simple announcement ("This paragraph is about...") nor a narrow factual statement ("Addis Ababa is in Ethiopia"). It must make a defensible controlling claim.
• Test questions frequently ask you to spot the "irrelevant sentence" that destroys the unity of a given paragraph.
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 81
    ),
    ShortNote(
        id = "eng-u5",
        subjectId = "c5",
        subject = "Communicative English",
        unit = "Unit 5",
        title = "Academic Essay Writing, Synthesizing & APA/MLA Referencing",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
An essay is a formal piece of academic writing composed of multiple paragraphs examining, interpreting, or arguing a specific topic. Academic integrity requires avoiding plagiarism through proper synthesis and citation.

🔍 DETAILED BREAKDOWN
1. Three-Part Structure of an Academic Essay:
   • Introductory Paragraph:
     - Hook / Attention Grabber: Interesting fact, thought-provoking question, or relevant quotation.
     - Background Context: General orientation to the subject matter.
     - Thesis Statement: The central, arguable claim of the entire essay; typically placed as the final sentence of the introduction.
   • Body Paragraphs (PEEL Framework):
     - Point: Clear topic sentence stating sub-argument.
     - Evidence: Supporting empirical data, quotations, statistics, or case studies.
     - Explanation: In-depth analysis explaining HOW the evidence proves the point.
     - Link: Connecting back to the overarching thesis or transitioning to next paragraph.
   • Concluding Paragraph:
     - Restatement of Thesis in fresh phrasing.
     - Synthesis: Summarizing major arguments without introducing brand new evidence.
     - Final Thought: Broader implication, recommendation, or call to reflection.

2. Essay Genres:
   • Expository: Explains and informs objectively.
   • Argumentative / Persuasive: Defends a debatable stance using logical evidence while acknowledging and refuting counterarguments.
   • Narrative: Recounts a meaningful sequence of events.
   • Descriptive: Vivid sensory depictions of people, places, or phenomena.

3. Academic Integrity & Avoiding Plagiarism:
   • Plagiarism: Presenting another author's words, ideas, or data as one's own without appropriate attribution.
   • Paraphrasing: Restating source ideas in one's own words and sentence structure while preserving original meaning (requires citation).
   • Summarizing: Condensing a lengthy source passage to its bare essentials (requires citation).
   • Direct Quotation: Copying exact words enclosed within quotation marks (used sparingly, requires page number).
   • Standard Citation Styles:
     - APA (American Psychological Association - Author, Date): (Alemu, 2023, p. 45).
     - MLA (Modern Language Association - Author, Page): (Alemu 45).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The thesis statement is the most critical sentence in the essay; it must state a specific, arguable claim, not a plain uncontested fact.
• Paraphrasing requires changing BOTH the vocabulary AND the grammatical sentence structure of the original passage.
        """.trimIndent(),
        colorHex = 0xFF059669,
        initialLikes = 88
    ),

    // =========================================================================
    // 6. GEOGRAPHY OF ETHIOPIA & THE HORN (c6, Units 1 - 5)
    // =========================================================================
    ShortNote(
        id = "geo-u1",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 1",
        title = "Location, Size, Shape & Geopolitical Significance",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia is an ancient country located in the Horn of Africa. Its geographical location, substantial landmass, compact shape, and geopolitical positioning relative to the Red Sea and Nile Basin shape its climate, history, and foreign policy.

🔍 DETAILED BREAKDOWN
1. Astronomical & Relative Location:
   • Astronomical (Absolute) Location: Lies between 3°N to 15°N latitudes and 33°E to 48°E longitudes.
     - North-South extent: ~12° (approx. 1,339 km).
     - East-West extent: ~15° (approx. 1,639 km).
     - Implication: Being entirely within tropical latitudes, solar angles are high year-round; however, tropical temperatures are modified by elevation.
   • Relative Location: Located in the Horn of Africa; bordered by 6 sovereign states: Eritrea (North), Djibouti (Northeast), Somalia (East & Southeast), Kenya (South), South Sudan (West), and Sudan (Northwest).

2. Size and Compact Shape:
   • Total Area: Approx. 1,106,000 km² (10th largest in Africa, 27th largest globally).
   • Compact Shape: Ethiopia has a compact, circular-like shape.
     - Indices of compactness (e.g., Compactness Ratio, Circularity Ratio).
     - Advantages: Shorter perimeter boundary relative to area, lower defense and border patrolling costs, efficient central administration from centrally located capital (Addis Ababa), shorter transport radials.

3. Geopolitical Implications & Strategic Significance:
   • Proximity to Global Trade Corridors: Located adjacent to the Bab-el-Mandeb strait and Red Sea shipping route connecting the Indian Ocean with Mediterranean Europe.
   • Landlocked Status: Landlocked since Eritrea's separation in 1993. Relies heavily on the Port of Djibouti (~95% of foreign maritime trade), alongside transit agreements with Berbera (Somaliland) and Lamu (Kenya).
   • Water Tower of Northeast Africa: Upper riparian state supplying over 85% of Nile river waters, carrying immense diplomatic leverage and regional hydro-political responsibility.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Ethiopia's shape is classified as COMPACT (not elongated, fragmented, or perforated).
• Despite lying in the tropics (3°N - 15°N), Ethiopia's high altitude significantly cools its climate, overriding purely latitudinal expectations.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 83
    ),
    ShortNote(
        id = "geo-u2",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 2",
        title = "Geology, Geomorphology & Physiographic Divisions",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia's complex physical relief is the product of millions of years of geological activity, including ancient crystalline basement formation, Mesozoic sedimentary transgressions, and Cenozoic tectonic uplift, volcanism, and rifting.

🔍 DETAILED BREAKDOWN
1. Geological Eras in Ethiopia:
   • Precambrian Era (4.5 billion - 600 million years ago):
     - Oldest era; formed the crystalline basement complex (granites, gneisses, schists).
     - Contains primary metallic minerals (gold, platinum, iron, tantalum).
   • Paleozoic Era (600 - 250 million years ago):
     - Era of prolonged denudation and peneplanation. Rocks were eroded down to flat plains; no major rock formation except glacial deposits in Northern Tigray.
   • Mesozoic Era (250 - 66 million years ago):
     - Marine Transgression & Regression of the Indian Ocean:
       1. Transgression (land subsided): Deposited Adigrat Sandstone first, followed by fossil-rich Antalo Limestone.
       2. Regression (land uplifted): Deposited Upper Sandstone.
       - Sedimentary rocks are thickest in the southeast (Ogaden basin) and central highlands.
   • Cenozoic Era (66 million years ago - Present):
     - Tertiary Period: Massive epeirogenic uplift of the Arabo-Ethiopian swell, followed by immense Trap Series basaltic fissure volcanism (Simien and Bale mountains).
     - Formation of the Great East African Rift Valley due to tensional tectonic faulting dividing the plateau.
     - Quaternary Period: Recent rifting, Afar depression volcanism (Erta Ale active shield volcano), and alluvial deposition.

2. Major Physiographic Divisions of Ethiopia:
   • 1. Western Highlands and Lowlands:
     - Western Highlands: Includes Tigray Plateau, North-Central Massifs (Ras Dejen, 4,550m in Simien), Shewan Plateau, and Southwestern Highlands.
     - Western Lowlands: Tekeze, Abay-Dinder, and Baro-Akobo plains along Sudan border.
   • 2. Southeastern Highlands and Lowlands:
     - Southeastern Highlands: Hararghe Plateau, Arsi-Bale Massif (Mount Batu / Tullu Dimtu, 4,377m), and Sidama Highlands.
     - Southeastern Lowlands: Ogaden, Wabi Shebelle, and Genale plains.
   • 3. The Rift Valley:
     - Divides highlands diagonally into NW and SE blocks.
     - Three subdivisions: Afar Triangle (Danakil Depression, Dallol at -125m bsl), Main Ethiopian Rift (central lakes area), and Chew Bahir Rift.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Geological sequence of Mesozoic rocks from oldest to youngest: Adigrat Sandstone (bottom) -> Antalo Limestone (middle) -> Upper Sandstone (top).
• Ras Dejen (4,550m) is the highest peak in Ethiopia (Western Highlands), while Dallol (-125m) is the lowest point (Afar Depression).
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 89
    ),
    ShortNote(
        id = "geo-u3",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 3",
        title = "Drainage Systems, Water Resources & Lake Basins",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia is renowned as the "Water Tower of East Africa" due to its elevated topography, abundant orographic precipitation, and dense network of 12 major river basins discharging over 122 billion cubic meters of water annually.

🔍 DETAILED BREAKDOWN
1. The Three Major Drainage Systems:
   • 1. Western (Mediterranean) Drainage System:
     - Major rivers: Abay (Blue Nile), Tekeze, Baro-Akobo, Mereb.
     - Accounts for approx. 60% of Ethiopia's total annual runoff.
     - Flow direction: Westward into the Nile River, terminating in the Mediterranean Sea.
     - Abay originates from Lake Tana springs (Gish Abay), joined by major tributaries (Didessa, Dabus, Fincha, Guder).
     - Baro River is the ONLY navigable river in Ethiopia during high-water seasons.
   • 2. Southeastern (Indian Ocean) Drainage System:
     - Major rivers: Wabe Shebelle and Genale-Dawa.
     - Accounts for approx. 32% of total annual runoff.
     - Flow direction: Southeast across Somali region toward the Indian Ocean.
     - Genale and Dawa join at the Somali border to form the Juba River; Wabe Shebelle dries up in the sands of coastal Somalia before reaching the sea.
   • 3. Inland (Rift Valley) Drainage System:
     - Major rivers: Awash River, Omo-Gibe, Bilate.
     - Accounts for approx. 8% of runoff; rivers do not reach the ocean (endorheic basins).
     - Awash River originates in Shewan plateau, irrigates large commercial farms (Wonji, Metehara), and terminates in Lake Abbe.
     - Omo River flows south into Lake Turkana in Kenya.

2. Lakes of Ethiopia:
   • Highland Lakes (Natural/Volcanic/Tectonic):
     - Lake Tana: Largest freshwater lake in Ethiopia (~3,600 km²), source of Blue Nile.
     - Crater Lakes: Wonchi, Bishoftu lakes, Ziquala (formed by volcanic caldera explosions).
   • Rift Valley Lakes:
     - Northern group: Ziway, Langano (brown, bilharzia-free), Abijatta (soda lake), Shalla (deepest lake, ~266m).
     - Southern group: Awassa, Abaya (largest Rift lake), Chamo (crocodile market).

3. Economic Significance & Hydroelectric Potential:
   • High elevation drops generate enormous hydropower potential (over 45,000 MW). Key projects: GERD (Grand Ethiopian Renaissance Dam, ~5,150 MW), Gilgel Gibe series, Tekeze dam.
   • Irrigation opportunities vs transboundary hydro-political tensions (e.g., Nile Basin Initiative, GERD diplomacy).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The Western drainage system carries the LARGEST volume of runoff (>60%), dominated by the Abay basin.
• Shalla is the DEEPEST lake in Ethiopia (~266m); Tana is the LARGEST by surface area; Langano is the ONLY bilharzia-free Rift lake.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 94
    ),
    ShortNote(
        id = "geo-u4",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 4",
        title = "Climate, Seasons & Traditional Agro-Ecological Zones",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Although Ethiopia lies wholly within the tropical zone, its climate exhibits extreme diversity ranging from alpine cold to blistering desert heat. Altitude is the overriding factor that modifies temperature and rainfall regimes.

🔍 DETAILED BREAKDOWN
1. Factors Controlling Ethiopian Climate:
   • Altitude (Elevation): The most decisive factor. Normal Environmental Lapse Rate: Temperature decreases by ~6.5°C for every 1,000m rise in altitude.
   • Latitude: Dictates tropical solar radiation and angle of incidence.
   • Pressure Systems & Monsoon Winds:
     - Inter-Tropical Convergence Zone (ITCZ): Seasonal oscillation of the ITCZ drives wet and dry seasons.
     - Equatorial Maritime (Southwesterly winds from Atlantic/Congo) bring heavy summer rains.
     - Tropical Maritime (Southeasterly winds from Indian Ocean) bring spring rains.
     - Northeast Trade Winds (dry continental from Asian high) bring winter dry season.

2. Ethiopian Seasons:
   • Kiremt (Summer: June, July, August): Main rainy season for most of the country, driven by southwesterlies following ITCZ migration northward.
   • Bega (Winter: December, January, February): Dry, sunny season with cold nights and frost in highlands, dominated by dry Northeast continental trades.
   • Belg (Autumn/Spring: March, April, May): Minor rainy season, especially crucial for south and southeastern highlands.
   • Meher (Harvest season: September, October, November).

3. Traditional Agro-Ecological Zones (Altitude-Based):
   • 1. Wurch / Kur (Alpine / Cold High Altitude):
     - Altitude: Above 3,200 meters. Mean annual temp: < 10°C.
     - Found on Simien (Ras Dejen) and Bale peaks. Frost-prone, sparse afro-alpine vegetation, limited barley cultivation, sheep rearing.
   • 2. Dega (Cool Highland):
     - Altitude: 2,300 to 3,200 meters. Mean annual temp: 10°C - 15°C.
     - Reliable rainfall; barley, wheat, pulses, temperate fruits; dense human settlement.
   • 3. Woina Dega (Temperate / Sub-Tropical Midland):
     - Altitude: 1,500 to 2,300 meters. Mean annual temp: 15°C - 20°C.
     - The ideal "comfort zone" supporting the highest population density; staple crops include Teff, maize, wheat, sorghum, coffee.
   • 4. Kolla (Warm Semi-Arid Lowland):
     - Altitude: 500 to 1,500 meters. Mean annual temp: 20°C - 27°C.
     - High temperatures, erratic rainfall; drought-resistant sorghum, millet, livestock pastoralism.
   • 5. Bereha (Hot Arid Desert):
     - Altitude: Below 500 meters. Mean annual temp: > 27°C.
     - Extreme heat, rainfall < 200 mm/year (Danakil Depression, lower Ogaden); nomadic pastoralism.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Woina Dega (1,500 - 2,300m) is considered the most favorable zone for human habitation in Ethiopia due to moderate temperatures, fertile soils, and absence of malaria.
• The ITCZ moves north of the equator in July, bringing Kiremt rains, and south of the equator in January, causing Bega dry conditions.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 86
    ),
    ShortNote(
        id = "geo-u5",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 5",
        title = "Population Dynamics, Settlement Patterns & Urbanization",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia is the second most populous nation in Africa (surpassing 125 million people). Its demographic structure features high natural growth, a youthful age distribution, and an overwhelmingly highland-concentrated rural population.

🔍 DETAILED BREAKDOWN
1. Demographic Characteristics:
   • High Population Growth: Rapid growth driven by high Crude Birth Rate (CBR) and declining Crude Death Rate (CDR), with an annual growth rate ~2.6%.
   • Age Structure: Pyramidal structure with a broad base; over 40% of the population is under 15 years old.
   • Dependency Ratio: High young-age dependency ratio puts immense pressure on educational, health, and economic infrastructure.
   • Fertility: Total Fertility Rate (TFR) remains elevated (~4.2 children per woman), though declining in urban centers.

2. Spatial Population Distribution:
   • High Altitudinal Concentration: Over 80% of Ethiopia's population resides in the highlands (> 1,500m elevation), despite highlands occupying only ~43% of total land area.
   • Explanatory Factors:
     - Reliable rainfall and cooler, comfortable climate.
     - Fertile volcanic agricultural soils.
     - Historical freedom from vector-borne tropical diseases, particularly malaria and trypanosomiasis (tsetse fly).
   • Lowland Areas: Characterized by sparse pastoralist populations due to aridity, extreme heat, and tropical disease prevalence.

3. Migration Trends:
   • Internal Migration:
     - Rural-to-Urban Migration: Driven by rural push factors (land scarcity, soil degradation, underemployment) and urban pull factors (higher education, modern employment, amenities).
     - Rural-to-Rural Migration: Seasonal agricultural labor migration (e.g., sesame picking in Humera, coffee harvesting in Oromia/Sidama).
   • External Migration: Outflows to Middle Eastern Gulf states, North America, Europe, and Southern Africa.

4. Urbanization in Ethiopia:
   • Paradox of Urbanization: Ethiopia has one of the LOWEST levels of urbanization in the world (~22% urban population), but one of the HIGHEST rates of urban growth (~4.5% annually).
   • Urban Primacy: Addis Ababa is a classic primate city, dwarfing the next largest cities (Dire Dawa, Hawassa, Adama, Bahir Dar, Mekelle) in population size, infrastructure, and economic power.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The single most important factor explaining the spatial distribution of Ethiopia's population is ALTITUDE (and its influence on climate, agriculture, and disease ecology).
• High youth dependency ratio means: (Population 0-14 + Population 65+) / (Working age population 15-64) × 100.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 83
    ),
    ShortNote(
        id = "geo-u6",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 6",
        title = "Economic Activities in Ethiopia (Agriculture, Industry, Trade & Tourism)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia's economy is structurally transitioning from subsistence agriculture to agro-industrial manufacturing and modern services. Agriculture remains the backbone, while industrial parks, trade logistics, and tourism drive national modernization.

🔍 DETAILED BREAKDOWN
1. The Agricultural Sector & Farming Systems:
   • Socio-Economic Stature: Employs approx. 68% of the national labor force, contributes ~33% of GDP, and generates over 75% of total foreign exchange earnings.
   • Major Agricultural Farming Systems:
     - 1. Highland Mixed Farming: Integrates crop cultivation with livestock husbandry in Dega and Woina Dega zones (>1,500m). Divided into:
       * Seed-Farming Complex: Grain crops (Teff, Wheat, Barley, Maize, Sorghum) predominantly in northern and central highlands.
       * Enset-Planting (Root Crop) Complex: Enset (false banana), root crops, and coffee in southern and southwestern highlands (highly drought-resilient food security system).
     - 2. Lowland Pastoralism & Nomadism: In arid Afar, Somali, and Borena lowlands (<1,500m); transhumance migration in search of water and pasture; reliance on camels, cattle, sheep, and goats.
     - 3. Commercial Plantation Agriculture: Large-scale irrigated farming along river valleys producing sugar cane (Wonji, Metehara, Kuraz), cotton (Awash, Omo), and floriculture/horticulture around Bishoftu and Rift lakes.
   • Livestock Population: Ethiopia possesses Africa's LARGEST livestock herd (over 65 million cattle, 50 million shoats, 8 million equines), crucial for draught power, milk, meat, and leather exports.

2. Manufacturing & Secondary Economic Activities:
   • Structure: Dominated by consumer light industries (food processing, beverages, textiles, apparel, leather, and cement).
   • Industrial Parks: Modern specialized zones (Hawassa Industrial Park, Bole Lemi, Kilinto, Kombolcha, Adama) established to attract Foreign Direct Investment (FDI) and boost manufactured exports.
   • Mineral Resources: Gold (Lega Dembi, Shakiso), Tantalum (Kenticha), Potash (Danakil Depression), Opal (Wollo), and Natural Gas (Calub and Hilala in Ogaden).

3. Trade, Infrastructure & Transport Logistics:
   • Internal & External Trade Balance:
     - Ethiopia experiences a persistent, severe Trade Deficit (merchandise imports far exceed merchandise exports).
     - Top Merchandise Exports: Coffee (largest foreign exchange earner, >30% of exports), Gold, Oilseeds (Sesame), Pulses, Cut Flowers, Chat, Leather products.
     - Major Merchandise Imports: Petroleum/fuel, machinery, capital equipment, industrial chemicals, fertilizers, metal products.
   • Transportation Network: Roads handle >95% of passenger and freight traffic. The electrified Ethio-Djibouti Railway (756 km) provides a vital trade corridor linking landlocked Ethiopia to maritime ports on the Red Sea/Gulf of Aden.

4. Tourism & National Heritage:
   • UNESCO World Heritage Cultural Sites: Rock-Hewn Churches of Lalibela, Obelisks of Axum, Fasil Ghebbi (Gondar Castles), Harar Jugol (Fortified Historic Town), Tiya Megalithic Stelae, Konso Cultural Landscape.
   • Natural Heritage Sites: Simien Mountains National Park, Bale Mountains National Park (inscribed 2023).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Coffee is Ethiopia's top foreign exchange earner, while Teff is the primary domestic cereal crop by cultivated area.
• Ethiopia's balance of merchandise trade is characterized by a STRUCTURAL DEFICIT due to high capital import bills and low-value primary commodity exports.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 92
    ),
    ShortNote(
        id = "geo-u7",
        subjectId = "c6",
        subject = "Geography of Ethiopia and the Horn",
        unit = "Unit 7",
        title = "Natural Resources, Environmental Hazards & Sustainable Conservation",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Ethiopia is endowed with rich natural resources including diverse soil types, unique biodiversity, and water basins. However, severe environmental hazards like soil erosion, deforestation, and climate extremes necessitate comprehensive conservation strategies.

🔍 DETAILED BREAKDOWN
1. Soil Types & Land Degradation:
   • Major Soil Orders in Ethiopia:
     - Nitisols: Deep, well-drained, reddish-brown tropical soils found on highland plateaus. High agricultural fertility; widely cultivated.
     - Vertisols (Black Cotton Soils): Heavy clay soils with high shrink-swell properties. Highly fertile but prone to severe waterlogging during Kiremt rainy season; crack extensively during dry Bega.
     - Fluvisols & Cambisols: Young alluvial soils deposited in river floodplains; highly suitable for commercial irrigation (Awash, Baro).
     - Lithosols & Regosols: Thin, shallow, stony soils found on steep eroded slopes; poor agricultural utility.
   • Soil Erosion Dynamics: Over 1.5 billion tons of topsoil eroded annually from the Ethiopian highlands due to steep topography, torrential rains, and vegetation clearance.
   • Soil Conservation Practices: Physical bunds, stone terraces, check dams, contour plowing, agroforestry, and biological grass strip planting.

2. Natural Vegetation & Deforestation:
   • Altitudinal Zonation of Vegetation:
     - Afro-Alpine & Sub-Afro-Alpine (>3,000m): Giant Lobelia (Lobelia rhynchopetalum), Erica arborea, tussock grasses.
     - Highland Coniferous Forest (2,200 - 3,000m): Tid (Juniperus procera), Zigba (Podocarpus falcatus).
     - Broadleaf Rainforest (1,500 - 2,500m): Moist montane forest of southwest (Kafa, Illubabor); wild Coffea arabica gene pool.
     - Acacia Woodland & Savanna (1,000 - 1,800m): Dominates Rift Valley and semi-arid mid-altitudes.
     - Steppe and Desert Scrub (<1,000m): Drought-resistant thorny shrubs and succulents in Afar and Somali lowlands.
   • Deforestation: Historical high-forest canopy declined dramatically; mitigated today by the nationwide Green Legacy Initiative.

3. Wildlife Conservation & Endemic Species:
   • Ethiopia's Globally Endemic Mammals:
     - Walia Ibex (Capra walie): Confined exclusively to cliff precipices of Simien Mountains.
     - Ethiopian Wolf (Canis simensis): World's rarest canid, primarily in Bale Mountains Afro-alpine plateau.
     - Mountain Nyala (Tragelaphus buxtoni): High-altitude woodland antelope of Arsi-Bale massifs.
     - Gelada Baboon (Theropithecus gelada): Grass-eating highland primate.
     - Swayne's Hartebeest: Protected in Senkelle Swayne's Hartebeest Sanctuary and Nechisar.
   • Protected Area Network: 27+ National Parks, Wildlife Sanctuaries (Babille Elephant Sanctuary), and UNESCO Biosphere Reserves (Kafa, Yayu, Lake Tana).

4. Environmental Hazards & Climate Resilience:
   • Recurrent Hazards: Lowland droughts causing famine and livestock death; seasonal flash floods in Awash basin, Gambella, and Dire Dawa.
   • Climate Resilience & Green Economy (CRGE) Strategy: National development policy targeting carbon-neutral middle-income status through renewable hydropower, solar, wind, and large-scale afforestation.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Nitisols are the deep red fertile highland soils; Vertisols are the black clay soils prone to waterlogging.
• The Ethiopian Wolf and Walia Ibex are flagship endemic species found in the Afro-alpine zones of Bale and Simien mountains respectively.
        """.trimIndent(),
        colorHex = 0xFF2563EB,
        initialLikes = 88
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
        title = "Nature of Economics, Scarcity & Production Possibility Frontier",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Economics is the social science studying how individuals and societies allocate scarce productive resources among competing unlimited human wants. It evaluates efficiency, opportunity cost, and decision-making under resource constraints.

🔍 DETAILED BREAKDOWN
1. The Fundamental Economic Problem:
   • Scarcity: Human material desires are virtually unlimited, whereas productive resources are finite.
   • Factors of Production (Inputs):
     - Land (natural resources, mineral wealth; earns Rent).
     - Labor (human mental and physical effort; earns Wages).
     - Capital (man-made tools, machinery, buildings; earns Interest).
     - Entrepreneurship (managerial initiative and risk-taking; earns Profit).
   • Choice & Opportunity Cost: Scarcity forces choices. Opportunity Cost is the value of the next best alternative forgone when making a decision.

2. Divisions & Methodologies of Economics:
   • Microeconomics: Analyzes individual decision-makers (consumers, households, firms, single markets).
   • Macroeconomics: Analyzes the economy as an aggregate whole (GDP, inflation, unemployment, fiscal and monetary policies).
   • Positive Economics: Objective, fact-based statements describing "what is" (empirically testable).
   • Normative Economics: Value-laden, subjective judgments prescribing "what ought to be" (not empirically testable).

3. Production Possibility Frontier (PPF):
   • A curve showing the maximum possible combinations of two goods an economy can produce given fixed technology and fully utilized resources.
   • Points on the Curve: Productively efficient (full employment of resources).
   • Points Inside the Curve: Inefficient (unemployment or underutilization of resources).
   • Points Outside the Curve: Unattainable with current resources and technology.
   • Shape of PPF: Concave to the origin (bowed outward) due to the Law of Increasing Opportunity Costs (resources are not equally adaptable to producing all goods).
   • Shifts in PPF: Technological advancements or growth in labor/capital shift PPF outward.

4. Major Economic Systems:
   • Traditional Economy: Decisions guided by custom, habit, and barter.
   • Command (Socialist) Economy: Central government planning authority dictates what, how, and for whom to produce (e.g., historical USSR, Derg Ethiopia).
   • Pure Market (Capitalist) Economy: Private ownership; resource allocation guided entirely by the price mechanism and invisible hand (Adam Smith).
   • Mixed Economy: Blends market mechanisms with state intervention/regulation (the model adopted in modern Ethiopia).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The slope of the PPF represents the Marginal Rate of Transformation (MRT), which measures Opportunity Cost.
• Positive statements can be proven or disproven by data; Normative statements express value judgments ("should" or "ought").
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 87
    ),
    ShortNote(
        id = "econ-u2",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 2",
        title = "Theory of Demand, Supply & Market Equilibrium",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Markets function through the interaction of buyers (Demand) and sellers (Supply). Market equilibrium determines the market price and quantity exchanged in competitive markets.

🔍 DETAILED BREAKDOWN
1. Theory of Demand:
   • Law of Demand: Other things being equal (ceteris paribus), as the price of a good rises, the quantity demanded falls; as price falls, quantity demanded rises (inverse relationship).
   • Reasons for Downward Slope: Substitution Effect (consumers switch to cheaper alternatives) and Income Effect (price decrease increases real purchasing power).
   • Demand Shifters (Change in Demand vs Change in Quantity Demanded):
     - Price change of the good causes MOVEMENT along the existing demand curve.
     - Non-price determinants SHIFT the entire curve: Consumer income (Normal goods shift right with income; Inferior goods shift left), Prices of related goods (Substitutes vs Complements), Tastes and preferences, Future price expectations, Number of buyers.

2. Theory of Supply:
   • Law of Supply: Other things being equal, as the price of a good rises, the quantity supplied increases; as price falls, quantity supplied decreases (direct relationship).
   • Supply Shifters: Input/resource prices, technological advancements, taxes and subsidies, expectations of producers, weather/natural conditions, number of sellers.

3. Market Equilibrium & Price Dynamics:
   • Equilibrium occurs where Quantity Demanded equals Quantity Supplied: Q_d = Q_s.
   • Market Disequilibrium:
     - Excess Supply (Surplus): Current price is ABOVE equilibrium. Sellers cut prices to clear unsold inventory.
     - Excess Demand (Shortage): Current price is BELOW equilibrium. Buyers bid prices up until balance is restored.
   • Simultaneous Shifts:
     - If both demand and supply increase simultaneously: Equilibrium quantity DEFINITELY increases, but equilibrium price is indeterminate (depends on relative magnitude of shifts).

4. Government Price Controls:
   • Price Ceiling: Maximum legal price set BELOW market equilibrium to protect consumers (e.g., rent control). Leads to permanent Market Shortage, black markets, and rationing.
   • Price Floor: Minimum legal price set ABOVE market equilibrium to protect producers (e.g., agricultural minimum prices, minimum wage). Leads to permanent Market Surplus.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• A change in PRICE causes a movement ALONG the curve. Non-price factors SHIFT the entire curve!
• Effective Price Ceiling is set BELOW equilibrium; Effective Price Floor is set ABOVE equilibrium.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 93
    ),
    ShortNote(
        id = "econ-u3",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 3",
        title = "Elasticity of Demand & Supply (Price, Income & Cross-Price)",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Elasticity measures the responsiveness or sensitivity of buyers and sellers to changes in price or income. It quantifies how much quantity demanded or supplied shifts in response to economic incentives.

🔍 DETAILED BREAKDOWN
1. Price Elasticity of Demand (PED):
   • Formula (Midpoint Method):
     PED = (%ΔQ_d) / (%ΔP) = [(Q₂ - Q₁) / ((Q₁ + Q₂)/2)] ÷ [(P₂ - P₁) / ((P₁ + P₂)/2)].
   • Classifications (using absolute value |e|):
     - Perfectly Inelastic (|e| = 0): Vertical demand curve (e.g., life-saving insulin).
     - Inelastic (0 < |e| < 1): Quantity changes proportionally less than price (e.g., basic necessities, salt).
     - Unit Elastic (|e| = 1): % change in Q equals % change in P.
     - Elastic (|e| > 1): Quantity changes proportionally more than price (e.g., luxuries, goods with close substitutes).
     - Perfectly Elastic (|e| = ∞): Horizontal demand curve.
   • Total Revenue (TR = P × Q) Test:
     - Inelastic Demand (|e| < 1): Price and TR move in the SAME direction (raise price => TR rises).
     - Elastic Demand (|e| > 1): Price and TR move in OPPOSITE directions (cut price => TR rises).
     - Unit Elastic (|e| = 1): Total Revenue is maximized and unchanged by small price shifts.

2. Cross-Price Elasticity of Demand (XED):
   • Formula: XED = (%ΔQ_d of Good X) / (%ΔP of Good Y).
   • Interpretation of Sign:
     - XED > 0 (Positive): Substitute Goods (e.g., tea & coffee; if coffee price rises, tea demand rises).
     - XED < 0 (Negative): Complementary Goods (e.g., cars & fuel; if fuel price rises, car demand falls).
     - XED = 0: Independent/unrelated goods.

3. Income Elasticity of Demand (YED):
   • Formula: YED = (%ΔQ_d) / (%ΔIncome).
   • Interpretation of Sign:
     - YED > 0: Normal Goods (demand increases as consumer income rises).
       * YED > 1: Luxury good.
       * 0 < YED < 1: Necessity good.
     - YED < 0: Inferior Goods (demand decreases as consumer income rises; e.g., low-grade grains).

4. Price Elasticity of Supply (PES):
   • Formula: PES = (%ΔQ_s) / (%ΔP). Always non-negative.
   • Primary determinant is TIME: Supply is highly inelastic in momentary/short run, and becomes increasingly elastic in long run as factories expand.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The sign matters! For Cross-Price Elasticity: Positive = Substitutes, Negative = Complements. For Income Elasticity: Positive = Normal, Negative = Inferior.
• For Price Elasticity of Demand, use the Total Revenue test: if a firm wants to increase revenue on an inelastic good, it MUST raise prices.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 89
    ),
    ShortNote(
        id = "econ-u4",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 4",
        title = "Theory of Production, Diminishing Returns & Cost Structures",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Firms combine productive inputs to generate outputs with the goal of profit maximization. Production theory explores input-output relationships in the short run and long run and their underlying cost dynamics.

🔍 DETAILED BREAKDOWN
1. Production Horizons & The Law of Diminishing Returns:
   • Short Run: Time period where at least one factor of production is FIXED (typically capital/plant size), while others are variable (labor).
   • Long Run: Time period long enough that ALL factors of production become VARIABLE.
   • Production Metrics:
     - Total Product (TP): Total volume of output produced.
     - Marginal Product (MP): Additional output produced by adding one more unit of variable labor: MP = ΔTP / ΔL.
     - Average Product (AP): Output produced per unit of labor: AP = TP / L.
   • Law of Diminishing Marginal Returns:
     - As successive units of a variable input (labor) are added to a fixed input (land/capital), a point is reached beyond which the additional output (MP) begins to decline.
   • Three Stages of Production:
     - Stage I (Increasing returns): MP > AP, and AP is rising. Inefficient to stop here.
     - Stage II (Diminishing returns): MP is declining but positive; AP is falling. RATIONAL operating zone for profit maximization.
     - Stage III (Negative returns): MP < 0 (total output drops). Irrational zone.

2. Short-Run Cost Structures:
   • Total Costs:
     - Total Fixed Cost (TFC): Overhead costs that do NOT vary with output (rent, insurance). Graph is horizontal line.
     - Total Variable Cost (TVC): Costs that vary directly with output volume (raw materials, hourly wages).
     - Total Cost: TC = TFC + TVC.
   • Average Costs:
     - Average Fixed Cost: AFC = TFC / Q (continually declines as output expands; spreads overhead).
     - Average Variable Cost: AVC = TVC / Q.
     - Average Total Cost: ATC = TC / Q = AFC + AVC.
   • Marginal Cost (MC):
     - Additional cost of producing one more unit of output: MC = ΔTC / ΔQ = ΔTVC / ΔQ.
   • Essential Geometric Property:
     - The Marginal Cost (MC) curve intersects BOTH the AVC and ATC curves at their ABSOLUTE MINIMUM points!

3. Long-Run Costs & Economies of Scale:
   • Long-Run Average Cost (LRAC) Envelope Curve:
     - Economies of Scale (Increasing Returns to Scale): As plant scale expands, LRAC declines (due to labor specialization, bulk purchasing discounts, efficient machinery).
     - Constant Returns to Scale: LRAC remains flat across output expansion.
     - Diseconomies of Scale (Decreasing Returns to Scale): As firm becomes overly large, LRAC rises (due to bureaucratic management bottlenecks, communication breakdowns).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The rational producer ALWAYS operates in STAGE II of production, where MP is decreasing but still positive.
• When MC < ATC, Average Total Cost is falling; when MC > ATC, Average Total Cost is rising; hence MC intersects ATC at its minimum!
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 95
    ),
    ShortNote(
        id = "econ-u5",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 5",
        title = "Market Structures (Competition to Monopoly) & Macroeconomic Overview",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Market structures define the competitive environment in which firms operate. Macroeconomics analyzes aggregate economic performance, focusing on national output (GDP), general price levels (inflation), and employment.

🔍 DETAILED BREAKDOWN
1. Spectrum of Four Market Structures:
   • Perfect Competition:
     - Huge number of buyers and sellers, homogeneous/identical product, free entry and exit, perfect market information.
     - Firm is a Price Taker: Faces perfectly horizontal elastic demand curve (P = MR = AR).
     - Profit-maximizing rule: Produce where P = MR = MC. Long-run economic profit is zero.
   • Monopoly:
     - Single seller, unique product with no close substitutes, extremely high barriers to entry (patents, natural monopolies, state licenses).
     - Firm is a Price Maker: Downward sloping demand curve. Marginal Revenue is less than Price (MR < P).
     - Produces lower output and charges higher prices than competitive markets, creating deadweight welfare loss.
   • Monopolistic Competition:
     - Many sellers, differentiated products (branding, packaging, design), low entry barriers.
     - Downward sloping demand curve; heavy non-price advertising competition. Long-run economic profit is zero.
   • Oligopoly:
     - Few dominant interdependent firms, significant entry barriers.
     - Mutual Interdependence: Actions of one firm trigger immediate rival reactions (analyzed via Game Theory, prisoner's dilemma, cartel collusion, kinked demand curve).

2. Core Macroeconomic Fundamentals:
   • Gross Domestic Product (GDP):
     - The total market value of all final goods and services produced within a country's geographical borders during a given year.
     - Expenditure Approach Formula: GDP = C + I + G + (X - M).
       * C = Household Consumption
       * I = Gross Private Business Investment
       * G = Government Purchases of goods & services
       * (X - M) = Net Exports (Exports minus Imports).
     - Nominal GDP (measured in current prices) vs Real GDP (adjusted for inflation using constant base-year prices).
   • Inflation:
     - A sustained, generalized increase in the overall price level across the economy, eroding purchasing power.
     - Measured using Consumer Price Index (CPI) and GDP Deflator.
     - Types: Demand-Pull Inflation (aggregate demand outpaces supply - "too much money chasing too few goods") vs Cost-Push Inflation (supply shocks or rising input costs push prices up).
   • Unemployment:
     - Frictional: Temporary unemployment of people transitioning between jobs.
     - Structural: Mismatch between workers' skills and demands of modern employers.
     - Cyclical: Caused by macroeconomic downturns and recessions in business cycle.
     - Natural Rate of Unemployment = Frictional + Structural (excludes cyclical).

💡 EXAM TIPS & HIGH-YIELD FOCUS
• Under Perfect Competition, P = MR; for all imperfect markets (Monopoly, Monopolistic, Oligopoly), MR < P.
• In calculating GDP, only FINAL goods are counted to prevent double counting of intermediate goods.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 103
    ),
    ShortNote(
        id = "econ-u6",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 6",
        title = "Macroeconomic Policy, Aggregate Demand/Supply & Fiscal Policy",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Macroeconomic stability is evaluated through the Aggregate Demand (AD) and Aggregate Supply (AS) framework. Governments utilize fiscal policy—adjusting public expenditure and taxation—to stabilize business cycles and eliminate output gaps.

🔍 DETAILED BREAKDOWN
1. Aggregate Demand & Aggregate Supply (AD-AS Framework):
   • Aggregate Demand (AD): Total quantity of final domestic goods and services demanded at various price levels. Downward sloping due to:
     - Real Wealth Effect (Pigou Effect): Lower prices increase real purchasing power of accumulated wealth, increasing consumption.
     - Interest Rate Effect (Keynes Effect): Lower prices reduce demand for money holdings, driving down interest rates and stimulating investment.
     - Exchange Rate Effect (Mundell-Fleming): Lower interest rates lead to capital outflows, depreciating domestic currency and boosting net exports.
   • Aggregate Supply (AS):
     - Short-Run Aggregate Supply (SRAS): Upward sloping because input costs (especially nominal wages) are sticky in the short run.
     - Long-Run Aggregate Supply (LRAS): Perfectly vertical at Potential / Natural GDP (Y_n), determined solely by labor, capital, natural resources, and technology (independent of general price level).
   • Macroeconomic Equilibrium & Gaps:
     - Recessionary Gap: Actual real GDP is BELOW potential output; characterized by cyclical unemployment.
     - Inflationary Gap: Actual real GDP EXCEEDS potential output; creates upward pressure on general wages and price levels.

2. The Keynesian Multiplier Concept:
   • Propensities:
     - Marginal Propensity to Consume: MPC = ΔC / ΔY (fraction of additional income spent on consumption).
     - Marginal Propensity to Save: MPS = ΔS / ΔY (fraction saved).
     - Fundamental Identity: MPC + MPS = 1.
   • Multiplier Formulas:
     - Expenditure / Government Spending Multiplier: k = 1 / (1 - MPC) = 1 / MPS.
     - Tax Multiplier: k_t = -MPC / (1 - MPC) = -MPC / MPS (always negative and smaller in magnitude than spending multiplier).
     - Balanced Budget Multiplier: If government spending and taxes increase simultaneously by the identical amount (ΔG = ΔT), the multiplier equals exactly 1.

3. Discretionary Fiscal Policy & Automatic Stabilizers:
   • Expansionary Fiscal Policy: Employed during recessions. Involves increasing government spending (G) or cutting taxes (T) to shift AD to the right, closing recessionary gaps.
   • Contractionary Fiscal Policy: Employed during rapid inflation. Involves cutting government spending or increasing taxes to shift AD to the left, cooling economic overheating.
   • Automatic Stabilizers: Built-in structural fiscal mechanisms that automatically cushion fluctuations without deliberate legislative action (e.g., progressive income tax brackets, unemployment compensation benefits).

4. Crowding-Out Effect & Public Debt:
   • Crowding-Out Mechanism:
     - Deficit-financed government spending increases public borrowing in loanable funds markets.
     - Increased demand for funds pushes real interest rates upward.
     - Higher interest rates discourage ("crowd out") private business capital investment and consumer borrowing, partially neutralizing the initial fiscal stimulus.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The Government Spending Multiplier is ALWAYS larger in magnitude than the Tax Multiplier because initial spending directly enters GDP, while part of a tax cut is saved (MPS).
• Long-Run Aggregate Supply (LRAS) is VERTICAL at full-employment output.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 91
    ),
    ShortNote(
        id = "econ-u7",
        subjectId = "c11",
        subject = "Introduction to Economics",
        unit = "Unit 7",
        title = "Money, Banking, Monetary Policy & International Economics",
        summary = """
📌 KEY CONCEPTS & OVERVIEW
Financial systems channel funds from savers to borrowers. Central banks conduct monetary policy to regulate money supply and interest rates, while international economics explores trade balance, comparative advantage, and foreign exchange regimes.

🔍 DETAILED BREAKDOWN
1. Nature, Evolution & Functions of Money:
   • Definition: Any financial asset universally accepted as a medium of exchange for goods, services, and repayment of debt.
   • Three Primary Functions of Money:
     1. Medium of Exchange: Eliminates the cumbersome "double coincidence of wants" inherent in barter trade.
     2. Unit of Account: Standard numerical monetary unit used to measure and compare the market value of disparate goods.
     3. Store of Value: Allows individuals to transfer purchasing power from present income to future consumption.
   • Money Supply Aggregates:
     - M1 (Narrow Money): Currency in circulation (banknotes + coins) + checkable / demand bank deposits.
     - M2 (Broad Money): M1 + savings deposits + small-denomination time deposits (quasi-money).

2. Fractional Reserve Banking & Money Creation:
   • Fractional Reserve System: Commercial banks are legally obligated to hold only a fraction of customer deposits in reserve, lending out the surplus (excess reserves).
   • Required Reserve Ratio (rr): Statutory percentage of deposits banks must keep as vault cash or deposits with the Central Bank.
   • Simple Money Multiplier:
     m = 1 / rr.
   • Maximum Potential Expansion of Money Supply:
     ΔMoney Supply = Initial Excess Reserves × (1 / rr).

3. Central Banking & Monetary Policy Instruments:
   • Central Bank (National Bank of Ethiopia - NBE): Sole issuer of national legal tender, banker and fiscal agent to the government, lender of last resort to commercial banks, manager of foreign exchange reserves.
   • Core Monetary Policy Instruments:
     1. Open Market Operations (OMO): Buying or selling government treasury securities. Purchasing bonds injects reserves into the banking system, expanding credit; selling bonds drains reserves.
     2. Required Reserve Ratio: Lowering rr expands commercial lending capacity (expansionary); raising rr restricts lending (contractionary).
     3. Discount / Policy Lending Rate: Interest rate central bank charges commercial banks for short-term emergency borrowing. Lower rates incentivize bank borrowing and credit expansion.
     4. Direct Credit Controls: Ceilings on commercial bank credit growth and targeted sector lending quotas.

4. International Economics, Trade & Exchange Rates:
   • Law of Comparative Advantage (David Ricardo): Nations gain from trade by specializing in goods they can produce at the LOWEST OPPORTUNITY COST, exchanging them for other goods.
   • Balance of Payments (BOP): Systematic accounting record of all economic transactions between domestic residents and the rest of the world over a year:
     - Current Account: Balance of trade in merchandise goods, services, net primary investment income, and unilateral secondary transfers (diaspora remittances).
     - Capital and Financial Account: Cross-border foreign direct investment (FDI), portfolio equity flows, and official external debt loans.
   • Foreign Exchange Systems:
     - Fixed (Pegged) Exchange Rate: Currency value pegged to major currency (e.g., USD) by central bank intervention.
     - Floating (Flexible) Exchange Rate: Currency value determined strictly by foreign exchange market supply and demand.
     - Currency Depreciation / Devaluation: Lowers foreign price of domestic exports, stimulating export competitiveness while making imports more expensive.

💡 EXAM TIPS & HIGH-YIELD FOCUS
• The simple money multiplier is the reciprocal of the reserve requirement ratio: m = 1 / rr.
• A country has a Comparative Advantage in producing a good if its OPPORTUNITY COST is lower than that of its trading partners, even if another nation has an Absolute Advantage in everything.
        """.trimIndent(),
        colorHex = 0xFF16A34A,
        initialLikes = 95
    )
)
