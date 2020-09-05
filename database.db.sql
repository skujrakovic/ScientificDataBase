BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "user" (
    "name" TEXT,
    "surname" TEXT,
    "email" TEXT,
    "username" TEXT,
    "password" TEXT,
    PRIMARY KEY ("username")
);
CREATE TABLE IF NOT EXISTS "scientific_paper" (
    "sid" INTEGER,
    "title" TEXT,
    "year" INTEGER,
    "genre" TEXT,
    "type" TEXT,
    "link" TEXT,
    "summary" TEXT,
    PRIMARY KEY("sid")
);
CREATE TABLE IF NOT EXISTS "author" (
    "aid" INTEGER,
    "full_name" TEXT,
    "fk_sid" INTEGER,
    PRIMARY KEY ("aid"),
    FOREIGN KEY ("fk_sid") REFERENCES scientific_paper ("sid")
);
INSERT INTO "scientific_paper" VALUES (1,'Action Analysis for Animators',2012, 'ART', 'BOOK', 'https://www.sciencedirect.com/book/9780240812182/action-analysis-for-animators', 'Action Analysis is one of the fundamental princples of animation that underpins all types of animation: 2d, 3d, computer animation, stop motion, etc. This is a fundamental skill that all animators need to create polished, believable animation. An example of Action Analysis would be Shrek''s swagger in the film, Shrek. The animators clearly understood (through action analysis) the type of walk achieved by a large and heavy individual (the real) and then applied their observations to the animated character of an ogre (the fantastic). It is action analysis that enabled the animation team to visually translate a real life situation into an ogre''s walk, achieving such fantastic results. Key animation skills are demonstrated with in-depth illustrations, photographs and live action footage filmed with high speed cameras. Detailed Case Studies and practical assignments ground action analysis methodology with real life examples. Action Analysis for Animators is a essential guide for students, amateurs and professionals.');
INSERT INTO "author" VALUES(1, 'Chris Webster', 1);
INSERT INTO "scientific_paper" VALUES(2, 'Animated Realism', 2012, 'ART','BOOK', 'https://www.sciencedirect.com/book/9780240814391/animated-realism', 'With the development and accessibility of animation tools and techniques, filmmakers are blurring the boundaries between documentary filmmaking and animation. The intimacy, imperfection and charm of the animated form is providing live-action and animation directors with unique ways to tell stories, humanize events and convey information not easily adapted for live-action media. Animated Realism presents animation techniques as they apply to the documentary genre with an inspirational behind-the-scenes look at award-winning animated documentaries. Animators and documentary filmmakers alike will learn how to develop a visual style with animation, translate a graphic novel into a documentary and use 3D animation as a storytelling tool, all in the context of creating animated documentaries.');
INSERT INTO "author" VALUES (2, 'Judith Kriger', 2);
INSERT INTO "scientific_paper" VALUES (3, 'L''Anthropologie', NULL, 'ART', 'JOURNAL', 'https://www.journals.elsevier.com/lanthropologie', 'First published in 1890, l''Anthropologie remains one of the most important journals devoted to prehistoric sciences and paleoanthropology. It regularly publishes thematic issues, originalsarticles and book reviews.');
INSERT INTO "author" VALUES (3, 'Henry De Lumley', 3);
INSERT INTO "scientific_paper" VALUES (4, 'Acidic Proteins of the Nucleus', 1974, 'BIOLOGY', 'BOOK', 'https://www.sciencedirect.com/book/9780121569303/acidic-proteins-of-the-nucleus', 'Acidic Proteins of the Nucleus focuses on the functional role of acidic nuclear proteins in differential gene expression. Historically, these proteins are referred to as acidic in nature because they are insoluble in dilute mineral acids and their amino acid composition shows a preponderance of acidic over basic amino acid residues. After an introduction to DNA-binding proteins and transcriptional control in prokaryotic and eukaryotic systems, the subsequent chapters describe various approaches for isolating, separating, and characterizing acidic nuclear proteins. The core chapters specifically cover the isolation, fractionation, and characterization of acidic nuclear phosphoproteins, and the role of these proteins in cell proliferation, cell differentiation, and cell cycle. The last two chapters address the role of acidic nuclear protein in binding steroid hormones and in gene regulation. Each chapter contains some previously unpublished work and provides recommendations for future research. This book will be a good reference background for researchers of acidic nuclear proteins.');
INSERT INTO "author" VALUES (4, 'Ivan L. Cameron', 4);
INSERT INTO "author" VALUES (5, 'James R. Jeter, Jr.', 4);
INSERT INTO "scientific_paper" VALUES (5, 'Advances in Water Resources', NULL, 'BIOLOGY', 'JOURNAL', 'https://www.journals.elsevier.com/advances-in-water-resources', 'Advances in Water Resources provides a forum for the presentation of fundamental scientific advances in the understanding of water resources systems. The scope of Advances in Water Resources includes any combination of theoretical, computational, and experimental approaches used to advance fundamental understanding of surface or subsurface water resources systems or the interaction of these systems with the atmosphere, geosphere, biosphere, and human societies. Manuscripts involving case studies that do not attempt to reach broader conclusions, research on engineering design, applied hydraulics, or water quality and treatment, as well as applications of existing knowledge that do not advance fundamental understanding of hydrological processes, are not appropriate for Advances in Water Resources.');
INSERT INTO "author" VALUES (6, 'P. D''Odorico', 5);
INSERT INTO "author" VALUES (7, 'G.C. Sander', 5);
INSERT INTO "scientific_paper" VALUES (6, 'Accounting Principles and Practice', 1964, 'ECONOMICS', 'BOOK', 'https://www.sciencedirect.com/book/9780080103327/accounting-principles-and-practice', 'Accounting Principles and Practice describes the principles and conventions which provide the structure of Accounting practice. Many of the questions are taken from the past examinations of the Royal Society of Arts, the Association of Certified and Corporate Accountants, the Society of Incorporated Accountants, and the Institute of Chartered Accountants. This book is divided into 19 chapters and begins with a brief introduction to the double entry system of accounting. Considerable chapters are devoted to the Accounting techniques concerning the management of different types of accounts, receipts, payments, and expenditures. Other chapters examine the validity of the principles involved and of the limitations of the conventions. This text is based upon the conviction that the oft-quoted distinction between theory and practice is disastrously misleading. It demonstrates that good theory and good practice are inseparable. This book will prove useful to accountants and accounting students.');
INSERT INTO "author" VALUES (8, 'S. Hall', 6);
INSERT INTO "scientific_paper" VALUES (7, 'Central Bank Review', NULL, 'ECONOMICS', 'JOURNAL', 'https://www.journals.elsevier.com/central-bank-review', 'Central Bank Review (CBR) seeks to publish articles of interest to practitioners and policy-makers as well as academics, and to do this, prioritizes articles which address specific policies implemented by central banks. Topics of particular interest relate to the primary responsibilities of central banks: macroeconomic stability; financial stability; liquidity management; payment, clearings and settlement systems; and reserve management. CBR intends to publish papers which emphasize on policy implications relating, directly or indirectly, to these central bank responsibilities. Topics will be wide-ranging across: macroeconomics, monetary economics, financial and capital markets, banking and financial intermediation, macro and micro prudential regulations and supervision, economic and econometric modeling and international finance and trade.');
INSERT INTO "author" VALUES (9, 'Çağrı Sarıkaya', 7);
INSERT INTO "author" VALUES (10, 'Cevriye Aysoy', 7);
INSERT INTO "scientific_paper" VALUES (8, 'Active Nitrogen', 1968, 'CHEMISTRY', 'BOOK', 'https://www.sciencedirect.com/book/9781483167381/active-nitrogen', 'Physical Chemistry, A Series of Monographs: Active Nitrogen presents the methods by which active nitrogen may be produced. This book is composed of five chapters that evaluate the energy content, molecular spectrum, and the emission of active nitrogen. Some of the topics covered in the book are the summary of light-emitting systems of active nitrogen; analysis of Long-Lived Lewis-Rayleigh Afterglow theory and Ionic theory of Mitra; reactions followed by induced light emission; and characteristics of homogeneous recombination. Other chapters deal with the analysis of metastable molecule theories and the mechanisms for reactions of active nitrogen involving direct N(4S) attack. The discussion then shifts to the rate constants for reactions induced by direct N(4S) attack. The evaluation of the Short-Lived Energetic Afterglow theory is presented. The final chapter is devoted to the examination of emission from molecular species with electronic energy levels below 9.76 eV. The book can provide useful information to physicists, students, and researchers.');
INSERT INTO "author" VALUES (11, 'A. Nelson Wright', 8);
INSERT INTO "author" VALUES (12, 'Carl A. Winkler', 8);
INSERT INTO "scientific_paper" VALUES (9, 'Carbohydrate Polymers', NULL, 'CHEMISTRY', 'JOURNAL', 'https://www.journals.elsevier.com/carbohydrate-polymers', 'Carbohydrate Polymers is a major journal within the field of glycoscience, and covers the study and exploitation of polysaccharides which have current or potential application in areas such as bioenergy, bioplastics, biomaterials, biorefining, chemistry, drug delivery, food, health, nanotechnology, packaging, paper, pharmaceuticals, medicine, oil recovery, textiles, tissue engineering and wood, and other aspects of glycoscience.');
INSERT INTO "author" VALUES (13, 'John F. Kennedy', 9);
INSERT INTO "author" VALUES (14, 'Manuel Coimbra',9);
INSERT INTO "scientific_paper" VALUES (10, 'Advanced Programming Methodologies', 1989, 'ENGINEERING', 'BOOK', 'https://www.sciencedirect.com/book/9780121746902/advanced-programming-methodologies','Advanced Programming Methodologies consists of lecture demos and practical experiments from the Summer School on Advanced Programming Methodologies which took place in Rome, Italy, on September 17-24, 1987. The school focused on tools of advanced programming as well as theoretical foundations for software engineering. Problems connected with implementation and application of high-level programming languages are highlighted. Comprised of 11 chapters, this volume first looks at two software development projects at the Institute of Informatics of the University of Warsaw in Poland, with emphasis on the methodologies used in programming and implementation. The reader is then introduced to flexible specification environments; object-oriented programming; and Paragon''s type hierarchies for data abstraction. Subsequent chapters focus on the inheritance rule in object-oriented programming; a functional programming approach to modularity in large software systems; database management systems; and relational algebra and fixpoint computation for logic programming implementation. The book also examines modules in high-level programming languages before concluding with a chapter devoted to storage management. This book is intended for computer programmers, undergraduate students taking various courses in programming, and advanced students of computer science.');
INSERT INTO "author" VALUES (15, 'Gianna Cionni', 10);
INSERT INTO "author" VALUES (16, 'Andrzej Salwicki', 10);
INSERT INTO "scientific_paper" VALUES (11, 'Advanced Engineering Informatics', NULL, 'ENGINEERING', 'JOURNAL', 'https://www.journals.elsevier.com/advanced-engineering-informatics', 'Advanced computing methods and related technologies are changing the way engineers interact with the information infrastructure. Explicit knowledge representation formalisms and new reasoning techniques are no longer the sole territory of computer science. For knowledge-intensive tasks in engineering, a new philosophy and body of knowledge called Engineering Informatics is emerging.');
INSERT INTO "author" VALUES (17, 'C. H. Chen', 11);
INSERT INTO "author" VALUES (18, 'T. Hartmann', 11);
INSERT INTO "scientific_paper" VALUES (12, 'Achieving Market Integration', 2004, 'BUSINESS', 'BOOK', 'https://www.sciencedirect.com/book/9780750657457/achieving-market-integration', 'Best execution, market integration, and other major financial market issues have traditionally been dealt with as separate matters requiring individual solutions. In Achieving Market Integration the author demonstrates the interrelated nature of these and other imperative problems, and sensibly reduces them to their common fundamental principles. Beginning with an in-depth examination of best execution in today''s multiple-market environment, the book moves logically into an examination of market structure and the problems of achieving genuine integration. The book makes the case that order interaction is fundamental to addressing each of these issues, and develops a unified regulatory approach to achieve true market integration based on intermarket linkages and a cross-market best execution policy. This unique approach culminates in a coherent set of policy recommendations and an innovative framework for assessing the effectiveness of future policy proposals.');
INSERT INTO "author" VALUES (19, 'Scott McCleskey', 12);
INSERT INTO "scientific_paper" VALUES (13, 'Business Horizons', NULL, 'BUSINESS', 'JOURNAL', 'https://www.journals.elsevier.com/business-horizons', 'Business Horizons is the bimonthly journal of the Kelley School of Business, Indiana University. The editorial aim is to publish original articles of interest to business academicians and practitioners. Articles cover a wide range of topical areas within the general field of business, with emphasis on identifying important business issues or problems and recommending solutions that address these. Ideally, articles will prompt readers to think about business practice in new and innovative ways. Business Horizons fills a unique niche among business publications of its type by publishing articles that strike a balance between the practical and the academic. To this end, articles published in Business Horizons are grounded in scholarship, yet are presented in a readable, non-technical format such that the content is accessible to a wide business audience.');
INSERT INTO "author" VALUES (20, 'Gregory C. Fisher', 13);
INSERT INTO "scientific_paper" VALUES (14, 'Accurate Results in the Clinical Laboratory', 2014, 'MEDICINE', 'BOOK', 'https://www.sciencedirect.com/book/9780124157835/accurate-results-in-the-clinical-laboratory', 'This practical, easy-to-use guide, named to Doody’s Core Titles 2013, addresses interference issues in all laboratory tests, including patient epigenetics, process of specimen collection, enzymes, biomarkers. Clinicians and laboratory scientists can therefore rely on one reference which speaks to both their needs of accurate specimen analysis and optimal patient care. Erroneous hospital and pathology laboratory results can be confusing and problematic, especially in acute care situations. While some factors creating interference, can be identified in the laboratory, detecting many others is often dependent on clinical details unavailable to the laboratory scientists or pathologists. Therefore, clinicians must become proficient in identifying such erroneous reports, and working with pathologists and laboratory scientists so that they can understand the source of such interferences, correct the results, and then decide what course of action must be followed for proper patient management.');
INSERT INTO "author" VALUES (21, 'Amitava Dasgupta', 14);
INSERT INTO "author" VALUES (22, 'Jorge L. Sepulveda', 14);
INSERT INTO "scientific_paper" VALUES (15, 'Academic Radiology', NULL, 'MEDICINE', 'JOURNAL', 'https://www.journals.elsevier.com/academic-radiology', 'Academic Radiology publishes original reports of clinical and laboratory investigations in diagnostic imaging, the diagnostic use of radioactive isotopes, computed tomography, positron emission tomography, magnetic resonance imaging, ultrasound, digital subtraction angiography, image-guided interventions, and related techniques. It also includes brief technical reports describing original observations, techniques, and instrumental developments; state-of-the-art reports on clinical issues, new technology and other topics of current medical importance; meta-analyses; scientific studies and opinions on radiologic education; and letters to the Editor.');
INSERT INTO "author" VALUES (23, ' N. Reed Dunnick', 15);
INSERT INTO "scientific_paper" VALUES (16, 'Active Control of Vibration', 1996, 'PHYSICS', 'BOOK', 'https://www.sciencedirect.com/book/9780122694400/active-control-of-vibration', 'This book is a companion text to Active Control of Sound by P.A. Nelson and S.J. Elliott, also published by Academic Press. It summarizes the principles underlying active vibration control and its practical applications by combining material from vibrations, mechanics, signal processing, acoustics, and control theory. The emphasis of the book is on the active control of waves in structures, the active isolation of vibrations, the use of distributed strain actuators and sensors, and the active control of structurally radiated sound. The feedforward control of deterministic disturbances, the active control of structural waves and the active isolation of vibrations are covered in detail, as well as the more conventional work on modal feedback. The principles of the transducers used as actuateors and sensors for such control strategies are also given an in-depth description. The reader will find particularly interesting the two chapters on the active control of sound radiation from structures: active structural acoustic control. The reason for controlling high frequency vibration is often to prevent sound radiation, and the principles and practical application of such techniques are presented here for both plates and cylinders. The volume is written in textbook style and is aimed at students, practicing engineers, and researchers.');
INSERT INTO "author" VALUES (24, 'C.R. Fuller', 16);
INSERT INTO "author" VALUES (25, 'S.J. Elliott', 16);
INSERT INTO "author" VALUES (26, 'P.A. Nelson', 16);
INSERT INTO "scientific_paper" VALUES (17, 'Applied Radiation and Isotopes', NULL, 'PHYSICS', 'JOURNAL', 'https://www.journals.elsevier.com/applied-radiation-and-isotopes', 'Applied Radiation and Isotopes provides a high quality medium for the publication of substantial, original and scientific and technological papers on the development and peaceful application of nuclear, radiation and radionuclide techniques in chemistry, physics, biochemistry, biology, medicine, security, engineering and in the earth, planetary and environmental sciences, all including dosimetry. Nuclear techniques are defined in the broadest sense and both experimental and theoretical papers are welcome. They include the development and use of α- and β-particles, X-rays and γ-rays, neutrons and other nuclear particles and radiations from all sources, including radionuclides, synchrotron sources, cyclotrons and reactors and from the natural environment.');
INSERT INTO "author" VALUES (27, 'Richard P. Hugtenburg', 17);
INSERT INTO "author" VALUES (28, 'Brian E. Zimmerman', 17);
INSERT INTO "scientific_paper" VALUES (18, 'Activity for Mental Health', 2020, 'PSYCHOLOGY', 'BOOK', 'https://www.sciencedirect.com/book/9780128196250/activity-for-mental-health', 'Activity For Mental Health explores all activities, including physical, social, natural, cognitive, art/hobby and music as a means to both preventing and treating mental illness. This book not only reviews evidence-based research behind activity, but also explores how these forms of activity can treat mental illnesses. First, the reader is introduced to the concepts of Formal Behavioral Activation Therapy (BAT) and informal activity as an effective treatment option. Case examples aid in connecting the benefits to real life scenarios. Following the introduction, each activity is introduced in separate chapters, including physical, social, natural, cognitive, art/hobby and music.');
INSERT INTO "author" VALUES (29, 'Brad Bowins', 18);
INSERT INTO "scientific_paper" VALUES (19, 'Addictive Behaviors', NULL, 'PSYCHOLOGY', 'JOURNAL', 'https://www.journals.elsevier.com/addictive-behaviors', 'Addictive Behaviors is an international peer-reviewed journal publishing high quality human research on addictive behaviors and disorders since 1975. The journal accepts submissions of full-length papers and short communications on substance-related addictions such as the abuse of alcohol, drugs and nicotine, and behavioral addictions involving gambling and technology. We primarily publish behavioral and psychosocial research, but our articles span the fields of psychology, sociology, psychiatry, epidemiology, social policy, medicine, pharmacology and neuroscience. While theoretical orientations are diverse, the emphasis of the journal is primarily empirical. That is, sound experimental design combined with valid, reliable assessment and evaluation procedures are a requisite for acceptance. However, innovative and empirically oriented case studies that might encourage new lines of inquiry are accepted as well. Studies that clearly contribute to current knowledge of etiology, prevention, social policy or treatment are given priority. Scholarly commentaries on topical issues, systematic reviews, and mini reviews are encouraged. We especially welcome multimedia papers that incorporate video or audio components to better display methodology or findings.');
INSERT INTO "author" VALUES (30, 'Marcantonio M. Spada', 19);

COMMIT;